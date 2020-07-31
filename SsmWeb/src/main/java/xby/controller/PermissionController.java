package xby.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import xby.domain.Permission;
import xby.service.IPermissionService;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private IPermissionService permissionService;
    @RequestMapping("/findAll")
    public ModelAndView findAll()
    {
        ModelAndView mv = new ModelAndView();
        List<Permission> permissions = permissionService.findAll();
        mv.addObject("permissionList",permissions);
        mv.setViewName("permission-list");
        return mv;
    }
    @RequestMapping("findById")
    public ModelAndView findById(@RequestParam(name="id",required = true)String permissionId)
    {
        ModelAndView mv = new ModelAndView();
        Permission permission = permissionService.findById(permissionId);
        mv.addObject("permission",permission);
        mv.setViewName("permission-show");
        return mv;

    }
    @RequestMapping("/deletePermission")
    public String delete(@RequestParam(name="id",required = true)String permissionId)
    {
        permissionService.deleteForeign(permissionId);
        permissionService.deleteById(permissionId);
        return "redirect:findAll";
    }

}
