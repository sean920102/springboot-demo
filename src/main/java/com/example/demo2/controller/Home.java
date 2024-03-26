package com.example.demo2.controller;

import com.example.demo2.Store;
import com.example.demo2.modal.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/")
@RestController
public class Home {
    @RequestMapping("")
    public String home(){
        return "This is homepage.";
    }

    @Autowired
    private Student student;

    @RequestMapping("/tt")
    public Student student(@RequestBody String name){
        student.setName(name);
        return student;
    }

    @RequestMapping("/store")
    public Store store(){
        Store store = new Store();
        List<String> list = new ArrayList<>();
        list.add("apple");
        list.add("orange");
        store.setProductList(list);
        return store;
    }
}
