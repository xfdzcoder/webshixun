package com.example.webshixun.config;

import cn.dev33.satoken.stp.StpUtil;
import com.example.oa_manage_system1.annotation.NeedAuth;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;


@Aspect
@Component //注册为组件类
public class NeedAuthConfig {


    //@Pointcut :从哪里切入    在有NeedAuth注解的方法上切入
    @Pointcut("@annotation(com.example.oa_manage_system1.annotation.NeedAuth)")
    public void needAuth(){}

    /**
     * 在有needAuth()注解的方法执行之前执行的方法
     * @param point
     * @throws IOException
     */
    @Before("needAuth()")
    public void beforeRequestInDirect(JoinPoint point) throws IOException {

        MethodSignature sign = (MethodSignature) point.getSignature();
        Method method = sign.getMethod();
        NeedAuth annotation = method.getAnnotation(NeedAuth.class);

//        if ( StpUtil.getLoginIdDefaultNull() == null ){
//            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
//            ServletOutputStream writer = response.getOutputStream();
//            response.setContentType("application/json;charset=UTF-8");
//            writer.println("need auth ");
//            writer.close();
//        } else
//            if (annotation.needAuth()){
                String[] needRole = annotation.needRole();
                List<String> roleList = StpUtil.getRoleList();
                if (!roleList.get(0).equals(needRole[0])){
                    HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
                    ServletOutputStream writer = response.getOutputStream();
                    response.setContentType("application/json;charset=UTF-8");
                    writer.println("need permission ");
                    writer.close();
                }
//            }

    }

}
