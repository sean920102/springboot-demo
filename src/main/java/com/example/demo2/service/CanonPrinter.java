package com.example.demo2.service;

import com.example.demo2.dao.Interface.Printer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class CanonPrinter implements Printer, InitializingBean {
    private int count;

    public void afterPropertiesSet() throws Exception{
        count = 5;
    }
    @Override
    public void Print(String message) {
        count--;
        System.out.println("Canon 剩餘次數 : "+count);
        System.out.println("Canon Print : "+message);
    }

    @Override
    public void PrintColor(String color){

    }
}
