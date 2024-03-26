package com.example.demo2.mapper;

import com.example.demo2.modal.Student;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRowMapper implements RowMapper<Student> {
    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        Integer id = rs.getInt("id");
        String name =  rs.getString("name");

        Student student = new Student();
        student.setId(id);
        student.setName(name);

        return student;
    }
}
