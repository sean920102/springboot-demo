package com.example.demo2;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class MyAspect {
    @Before("execution(* com.example.demo2.service.HpPrinter.*(..))")
    public void before(){
        System.out.println("I'm before.");
    }

    @After("execution(* com.example.demo2.service.HpPrinter.*(..))")
    public void after(){
        System.out.println("I'm after.");
    }

    @Around("execution(* com.example.demo2.service.HpPrinter.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable{

        Date start = new Date();
        System.out.println("Start."+start);
        //程式切入點
        Object obj = pjp.proceed();

        Date end = new Date();
        System.out.println("End."+end);
        return  obj;
    }
}
