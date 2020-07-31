package xby.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import xby.domain.Permission;
import xby.domain.Role;
import xby.service.IRoleService;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private IRoleService roleService;
    @RequestMapping("/findAll")
    public ModelAndView findAll()
    {
        ModelAndView mv = new ModelAndView();
        List<Role> roles = roleService.findAll();
        mv.addObject("roleList",roles);
        mv.setViewName("role-list");
    return mv;
    }

    @RequestMapping("/save")
    public String save(Role role)
    {
        ModelAndView mv = new ModelAndView();
        roleService.save(role);
        mv.setViewName("role-list");
        return "redirect:findAll";
    }

    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(name = "id",required = true)String roleId)
    {
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findById(roleId);

        mv.addObject("role",role);
                mv.setViewName("role-show");
        return mv;
    }

    @RequestMapping("/findRoleByIdAndAllPermission")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id",required = true)String roleId)
    {
        ModelAndView mv = new ModelAndView();
        List<Permission> permissions = roleService.findOtherPermissions(roleId);
        Role role = roleService.findById(roleId);
        mv.addObject("role",role);
        mv.addObject("permissionList",permissions);
        mv.setViewName("role-permission-add");
        return mv;
    }
    @RequestMapping("/addPermissionToRole")
    public String addPermissionToRole(@RequestParam(name = "roleId",required = true)String roleId,@RequestParam(name="ids")String []ids)
    {
        ModelAndView mv = new ModelAndView();

        System.out.println("controller:======"+roleId);

        roleService.addPermissionToRole(roleId,ids);

        return "redirect:findAll";
    }
}
