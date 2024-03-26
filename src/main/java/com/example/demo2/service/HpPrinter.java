package com.example.demo2.service;

import com.example.demo2.dao.Interface.Printer;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class HpPrinter implements Printer {
    private int count;

    @Value("${count}")
    private String number;

    @Value("${name:Sean}")
    private String name;

    @PostConstruct
    public void init(){
        count = 5;
    }
    @Override
    public void Print(String message) {
        count--;
        Date startTime = new Date();
        System.out.println("Hp剩餘列印次數: "+count);
        System.out.println("Hp Print : "+message);
        Date endTime = new Date();
        long time = endTime.getTime() - startTime.getTime();
        //System.out.println(time);
    }

    @Override
    public void PrintColor(String color){

        System.out.println("HP Printer (彩色)"+color);
    }
}
