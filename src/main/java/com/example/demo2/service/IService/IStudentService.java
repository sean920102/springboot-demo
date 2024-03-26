package com.example.demo2.service.IService;

import com.example.demo2.modal.Student;

public interface IStudentService {
    Student getById(Integer id);
    boolean insert(Student student);

    boolean Delete(Integer id);
}
