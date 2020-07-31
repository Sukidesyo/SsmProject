package xby.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import xby.domain.Role;
import xby.domain.UserInfo;
import xby.service.IUserService;

import javax.jws.WebParam;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;
    @RequestMapping("/findAll")
    public ModelAndView findAll()
    {
        ModelAndView mv =new ModelAndView();
        List<UserInfo> users = userService.findAll();
        mv.addObject("userList",users);
        mv.setViewName("user-list");
        return mv;
    }
    @RequestMapping("/save")
    public String saveUser(UserInfo userInfo)
    {

        userService.saveUser(userInfo);
        return "redirect:findAll";
    }
    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(name="id",required = true)String id)
    {
        ModelAndView mv = new ModelAndView();
        UserInfo user = userService.findById(id);
        System.out.println(user.toString());
        mv.addObject("user",user);
        mv.setViewName("user-show");
        return mv;
    }
    @RequestMapping("/findUserByIdAndAllRole")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name="id",required = true)String id)
    {
        ModelAndView mv = new ModelAndView();
        UserInfo user = userService.findById(id);
        List<Role> otherRoles = userService.findOtherRoles(id);
        mv.addObject("user",user);
        mv.addObject("roleList",otherRoles);
        mv.setViewName("user-role-add");

        return mv;
    }
    @RequestMapping("/addRoleToUser")
    public ModelAndView addRoleToUser(@RequestParam(required = true)String userId,@RequestParam(name="ids",required = true)String[] roleId)
    {
        ModelAndView mv = new ModelAndView();
        userService.addRoleToUser(userId,roleId);
        mv.setViewName("user-show");
        return mv;

    }
}
