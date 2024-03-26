package com.example.demo2.service;

public class Calculator {
    public int add(int x,int y){
        return x+y;
    }
    //    psvm
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        int result = calculator.add(1,2);
        System.out.println(result);
    }
}
