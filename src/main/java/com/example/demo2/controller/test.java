package com.example.demo2.controller;

import com.example.demo2.dao.Interface.Printer;
import com.example.demo2.modal.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/test")
@RestController
public class test {

    @Autowired
    @Qualifier("hpPrinter")
    private Printer printer;

    @RequestMapping("/")
    public String test1(){
        printer.Print("Hi");
        printer.PrintColor("green");
        return "Hi";
    }

    @RequestMapping("/getId")
    public Student getId(
        @RequestParam(name = "testId") Integer id,
        @RequestParam(required = false) String name,
        @RequestParam(defaultValue = "26") Integer age
    ){
        Student student = new Student();
        if(name==null){name="Sean";}
        student.setId(id);
        student.setName(name);
        student.setAge(age);
        return student;
    }

    @RequestMapping("/postbody")
    public Student postbody(@RequestBody Student res){
        if(res.getId()==null){
            throw new RuntimeException("id 不可為null");
        }
        return res;
    }

    @RequestMapping("/getHeader")
    public String getHeader(
            @RequestHeader String token,
            @RequestHeader(name="content-Type") String content
    ){
        return token;
    }

    @RequestMapping("/{id}")
    public Integer getId(@PathVariable Integer id){
        return id;
    }

    @RequestMapping("/ex")
    public String ex(){
        throw new RuntimeException("error");
    }
    @RequestMapping("/ex2")
    public String ex2(){

        throw new IllegalArgumentException("error");

    }
}
