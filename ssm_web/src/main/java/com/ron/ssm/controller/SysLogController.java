package com.ron.ssm.controller;

import com.ron.ssm.domain.Syslog;
import com.ron.ssm.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sysLog")
public class SysLogController {

    @Autowired
    private ISysLogService sysLogService;
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();

        List<Syslog> list = sysLogService.findAll();
        mv.addObject("sysLogs",list);
        mv.setViewName("syslog-list");

        return mv;
    }
}
