package com.example.demo2;

import com.example.demo2.dao.Interface.Printer;
import com.example.demo2.service.CanonPrinter;
import com.example.demo2.service.HpPrinter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Myconfiguration {
    @Bean
    public Printer MyPrinter(){
        return new HpPrinter();
    }

    @Bean
    public Printer yourPrinter(){
        return new CanonPrinter();
    }
}
