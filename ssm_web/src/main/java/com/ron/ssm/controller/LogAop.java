package com.ron.ssm.controller;


import com.ron.ssm.domain.Syslog;
import com.ron.ssm.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.UUID;

@Component
@Aspect//切面类
public class LogAop {
    private Date visitTime;
    private Class aClass;
    private Method method;

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ISysLogService sysLogService;
    //前置通知
    //获取开始时间,执行的类,执行的方法
    @Before("execution(* com.ron.ssm.controller.*.*(..))")
    public void doBefore(JoinPoint point) throws NoSuchMethodException {
        //*获取开始时间
        visitTime= new Date();

        //*获取具体访问的类对象
        aClass = point.getTarget().getClass();

        //访问的方法名
        String methodName = point.getSignature().getName();
        //获取执行方法的参数
        Object[] args = point.getArgs();

        //*获取执行的方法
        if (args.length==0 || args==null){
            //获取无参的方法
            method=aClass.getMethod(methodName);
        }else {
            Class[] classes = new Class[args.length];//创建一个class数组
            for (int i = 0; i <args.length ; i++) {//把参数数组遍历
                classes[i]=args[i].getClass();//把每个参数的class放入class数组
            }
            method = aClass.getMethod(methodName, classes);//获取有参方法
        }



    }



    //后置通知
    @After("execution(* com.ron.ssm.controller.*.*(..))")
    public void  doAfter(JoinPoint point) throws Exception {
        //*获取访问时常
        long time = new Date().getTime() - visitTime.getTime();

        String url="";

        if (aClass!=null && method!=null &&aClass!= LogAop.class &&aClass!=SysLogController.class){
            //获取类上面的RequestMapping注解
            RequestMapping classAnnotation = (RequestMapping)aClass.getAnnotation(RequestMapping.class);
            if (classAnnotation!=null){
                String[] classValue = classAnnotation.value();
                //获取方法上面的RequestMapping注解
                RequestMapping methodAnnotation = (RequestMapping)method.getAnnotation(RequestMapping.class);
                if (methodAnnotation!=null){
                    String[] methodValue = classAnnotation.value();
                    //*获取uri: = 类注解的value和方法注解的value
                    url=classValue[0]+methodValue[0];//拼接成URL

                    //*获取访问的ip地址
                    String ip = request.getRemoteAddr();

                    //*获取操作者
                    SecurityContext context = SecurityContextHolder.getContext();//从上下文中获取登录的用户
                    User user = (User) context.getAuthentication().getPrincipal();
                    String username = user.getUsername();


                    //封装进Syslog
                    Syslog syslog = new Syslog();
                    syslog.setId(UUID.randomUUID().toString().replace("-",""));
                    syslog.setExecutionTime(time);
                    syslog.setIp(ip);
                    syslog.setUrl(url);
                    syslog.setUsername(username);
                    syslog.setVisitTime(visitTime);
                    syslog.setMethod("[类名]:"+aClass.getName()+"[方法名]:"+method.getName());
                    //调用service完成数据库的操作
                    sysLogService.save(syslog);

                }
            }
        }



    }

}
