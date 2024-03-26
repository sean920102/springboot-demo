package com.example.demo2.dao;

import com.example.demo2.modal.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class StudentDaoTest {
    @Autowired
    private StudentDao studentDao;

    @Test
    public void getById(){
        Student student =studentDao.getById(3);
        assertNotNull(student);
        assertEquals("mike",student.getName());
    }

    @Transactional
    @Test
    public void deleteById(){
        studentDao.Delete(3);
        Student student = studentDao.getById(3);
        assertNull(student);
    }
}