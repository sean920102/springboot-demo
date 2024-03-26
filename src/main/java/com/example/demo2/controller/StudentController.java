package com.example.demo2.controller;

import com.example.demo2.mapper.StudentRowMapper;
import com.example.demo2.modal.Student;
import com.example.demo2.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/student")
@RestController
@Validated
public class StudentController {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private StudentService studentService;

    @GetMapping()
    public String home(){
        return "This is student.";
    }

    @PutMapping("/insert")
    public ResponseEntity<String>create(@RequestBody @Valid Student res){
        if(studentService.insert(res)){
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Insert Seccuss");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Insert failed");
    }


    @PutMapping("/insertList")
    public ResponseEntity<String> insertList(@RequestBody List<Student> res){
        String sql ="Insert INTO student(name,age) VALUE (:stu_name,:stu_age)";

        MapSqlParameterSource[] parameterSources = new MapSqlParameterSource[res.size()];
        System.out.println(res.get(0));
        for(int i=0;i<res.size();i++){
            Student student = res.get(i);
            parameterSources[i] = new MapSqlParameterSource();
            parameterSources[i].addValue("stu_name",student.getName());
            parameterSources[i].addValue("stu_age",student.getAge());
        }
        namedParameterJdbcTemplate.batchUpdate(sql,parameterSources);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Create success");
    }

    @GetMapping("/{id}")
    public Student query(@PathVariable Integer id){
        return studentService.getById(id);
    }
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        if(studentService.Delete(id)){
            return "Delete Seccuss";
        }
        return "Delete Failed";
    }

    @GetMapping("/queryall")
    public List<Student> select(){
        String sql = "SELECT id,name from student";
        Map <String,Object> map = new HashMap<>();

        List<Student> stu = namedParameterJdbcTemplate.query(sql,map,new StudentRowMapper());
        return stu;
    }

}
