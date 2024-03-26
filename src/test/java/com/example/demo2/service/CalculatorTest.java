package com.example.demo2.service;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {
    @BeforeAll
    public static void beforeAL(){
        System.out.println("before");
    }

    @Test
    public void add(){
        Calculator calculator = new Calculator();
        int result = calculator.add(1,2);
        assertEquals(3,result,"add error");
        System.out.println("add");
    }
    @Test
    public void test(){
        System.out.println("test");
    }
    @AfterAll
    public static void afterall(){
        System.out.println("after");
    }
}