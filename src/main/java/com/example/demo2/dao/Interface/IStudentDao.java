package com.example.demo2.dao.Interface;

import com.example.demo2.modal.Student;

public interface IStudentDao {
    Student getById(Integer id);

    boolean insert(Student student);

    boolean Delete(Integer id);
}
