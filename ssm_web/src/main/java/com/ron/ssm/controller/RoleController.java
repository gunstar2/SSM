package com.ron.ssm.controller;


import com.ron.ssm.domain.Permission;
import com.ron.ssm.domain.Role;
import com.ron.ssm.domain.RoleIdAndPermissionId;
import com.ron.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService service;

    //查询全部角色
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {

        ModelAndView mv = new ModelAndView();

        List<Role> all = service.findAll();

        mv.addObject("roleList",all);
        mv.setViewName("role-list");
        return mv;
    }


    //添加角色
    @RequestMapping("/save.do")
    public String save(Role role) throws Exception {
        service.save(role);
        return "redirect:findAll.do";
    }


    //角色详情
    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        Role role = service.findByid(id);
        modelAndView.addObject("role",role);
        modelAndView.setViewName("role-show");
        return modelAndView;
    }

    //删除角色根据id
    @RequestMapping("/deleteRoleById.do")
    public String deleteRoleById(@RequestParam(name = "id",required = true) String roleId) throws Exception {
        service.deleteRoleById(roleId);
        return "redirect:findAll.do";
    }

    //添加权限前的查询权限
    @RequestMapping("/findPermission.do")
    public ModelAndView findPermission(@RequestParam(name = "id",required = true) String roleId) throws Exception {

        ModelAndView mv  = new ModelAndView();
        List<Permission> list=service.findPermission(roleId);
        mv.addObject("permissionList", list);
        mv.addObject("roleId",roleId);

        mv.setViewName("add-permission");
        return mv;
    }


    //添加权限前的查询权限
    @RequestMapping("/addPermission.do")
    public String addPermission(  HttpServletRequest request) throws Exception {
        String roleId = request.getParameter("roleId");
        String[] ids = request.getParameterValues("ids");
        for (String id : ids) {
            RoleIdAndPermissionId  rp = new RoleIdAndPermissionId();
            rp.setPermissionId(id);
            rp.setRoleId(roleId);
            service.addPermission(rp);
        }

        return "redirect:findAll.do";
    }
}
