package com.ron.ssm.controller;


import com.ron.ssm.domain.Permission;
import com.ron.ssm.domain.UserInfo;
import com.ron.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;


    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {

        ModelAndView mv = new ModelAndView();
        List<Permission> list = permissionService.findAll();
        mv.addObject("permissionList",list);

        mv.setViewName("permission-list");

        return mv;
    }
    //添加权限
    @RequestMapping("/save.do")
    public String save(Permission permission) throws Exception {

        permissionService.save(permission);
        return "redirect:findAll.do";
    }

    //查询权限详情信息
    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        Permission permission = permissionService.findById(id);
        modelAndView.addObject("user",permission);
        modelAndView.setViewName("permission-show");
        return modelAndView;

    }

    //添加权限
    @RequestMapping("/deleteById.do")
    public String deleteById(@RequestParam(name = "id",required = true) String id) throws Exception {

        permissionService.deleteById(id);
        return "redirect:findAll.do";
    }

}
