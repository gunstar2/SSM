package com.ron.ssm.controller;

import com.ron.ssm.domain.Role;
import com.ron.ssm.domain.UserInfo;
import com.ron.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService service ;

    //查询所有用户
    @RequestMapping("findAll.do")
    @PreAuthorize("hasRole('ROLE_ADMIN')")//拥有ROLE_ADMIN角色才能查询
    public ModelAndView FindAll() throws Exception {
        ModelAndView modelAndView = new ModelAndView();

        List<UserInfo> list = service.findAll();
        modelAndView.addObject("userList",list);
        modelAndView.setViewName("user-list");
        return modelAndView;
    }

    //用户添加
    @RequestMapping("/save.do")
    @PreAuthorize("authentication.principal.username == 'ron'")//只有用户名为ron才能添加
    public String save(UserInfo userInfo) throws Exception {
        service.save(userInfo);

        return "redirect:findAll.do";

    }
    //指定id查询用户
    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        UserInfo info = service.findById(id);

        modelAndView.addObject("user",info);
        modelAndView.setViewName("user-show1");
        return modelAndView;

    }

    //根据id查询用户可以添加的角色
    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView  findUserByIdAndAllRole(@RequestParam(name = "id",required = true) String userId) throws Exception {
        //根据id查询用户
        UserInfo userInfo = service.findById(userId);
        // 根据id查询可以添加的角色
        List<Role> roleList = service.finOtherRoles(userId);
        ModelAndView mv = new ModelAndView();
        mv.addObject("user",userInfo);
        mv.addObject("roleList",roleList);

        mv.setViewName("user-role-add");
        return  mv;
    }

    //给用户添加角色
    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId",required = true) String userId,
                              @RequestParam(name = "ids",required = true) String[] roleIds) throws Exception {

        service.addRoleToUser(userId,roleIds);
        return "redirect:findAll.do";

    }

}






















