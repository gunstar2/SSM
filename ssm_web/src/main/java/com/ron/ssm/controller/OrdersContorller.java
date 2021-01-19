package com.ron.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.ron.ssm.domain.Orders;
import com.ron.ssm.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersContorller {

    @Autowired
    private IOrdersService ordersService;
    //查询全部未分页
    /*@RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView md = new ModelAndView();
        List<Orders> all = ordersService.findAll();
        md.addObject("ordersList",all);

        md.setViewName("orders-list");
        return md;

    }*/

    //产品分页查询
    @RequestMapping("/findAll.do")
    @Secured("ROLE_ADMIN")
    public ModelAndView findAll//获取url上属性值
            (@RequestParam(name = "page",required = true,defaultValue = "1")Integer page,
             @RequestParam(name = "size",required = true,defaultValue = "4")Integer size)
            throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Orders> ordersList = ordersService.findAll(page,size);
        //PageInfo为一个分页bean
        PageInfo pageInfo = new PageInfo(ordersList);

        mv.addObject("pageInfo",pageInfo);

        mv.setViewName("orders-page-list");
        return mv;

    }



    //订单详情:包含产品,会员,旅客
    @RequestMapping("/findById.do")
    public ModelAndView findAllById
            (@RequestParam(name = "id",required = true)String ordersId)throws Exception {
        ModelAndView mv = new ModelAndView();
        Orders orders = ordersService.findByid(ordersId);

        mv.addObject("orders",orders);
        mv.setViewName("orders-show");


        return mv;

    }




}
