package com.yolo.workdemo.common;

import com.yolo.workdemo.common.annotation.RateLimit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class RateLimitInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        //类上的注解
        RateLimit annotation = handlerMethod.getBean().getClass().getDeclaredAnnotation(RateLimit.class);
        //方法上的注解
        RateLimit methodAnnotation = handlerMethod.getMethodAnnotation(RateLimit.class);
        if (annotation == null && methodAnnotation == null) {
            System.out.println("没进入鉴权");
            return false;
        } else {
            System.out.println("鉴权");
            return true;
        }

//        return super.preHandle(request, response, handler);
    }


}
