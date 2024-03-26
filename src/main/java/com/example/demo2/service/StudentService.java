package com.example.demo2.service;

import com.example.demo2.modal.Student;
import com.example.demo2.dao.StudentDao;
import com.example.demo2.service.IService.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentService implements IStudentService {
    @Autowired
    private StudentDao studentDao;

    @Override
    public boolean insert(Student student) {
        return studentDao.insert(student);
    }

    @Override
    public boolean Delete(Integer id) {
        return studentDao.Delete(id);
    }

    @Override
    public Student getById(Integer id) {
        return studentDao.getById(id);
    }
}
