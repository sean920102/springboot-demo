package com.example.demo2.dao;

import com.example.demo2.dao.Interface.IStudentDao;
import com.example.demo2.modal.Student;
import com.example.demo2.mapper.StudentRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class StudentDao implements IStudentDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Override
    public Student getById(Integer id) {
        String countSql = "SELECT count(*) FROM student WHERE id = :id";
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        Integer count = namedParameterJdbcTemplate.queryForObject(countSql,map,Integer.class);
        if(count>0){
            Student student = new Student();
            String sql = "SELECT id, name FROM student WHERE id = :id";
            List<Student> list = namedParameterJdbcTemplate.query(sql,map,new StudentRowMapper());
            return list.get(0);
        }
        return null;
    }

    @Override
    public boolean insert(Student student) {
        try {
            Map<String,Object> map = new HashMap<>();
            String sql ="Insert INTO student(name,age) VALUE (:stu_name,:stu_age)";
            map.put("stu_name",student.getName());
            map.put("stu_age",student.getAge());
            KeyHolder keyHolder = new GeneratedKeyHolder();

            namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map),keyHolder);
            int id = keyHolder.getKey().intValue();

            return true;

        }catch (Exception ex){
            return false;
        }
    }

    @Override
    public boolean Delete(Integer id) {
        try {
            Map <String,Object> map = new HashMap<>();
            map.put("id",id);
            String sql ="Delete from student where id= :id";

            int i = namedParameterJdbcTemplate.update(sql,map);
            System.out.println(i);
            return true;

        }catch (Exception ex){
            return false;
        }
    }
}
