package com.example.demo2.middleware;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class MyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("middleware");
//        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return true;
        //return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
