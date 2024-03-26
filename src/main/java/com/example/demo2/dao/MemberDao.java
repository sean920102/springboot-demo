package com.example.demo2.dao;

import com.example.demo2.dao.Interface.IMember;
import com.example.demo2.mapper.MemberRowMapper;
import com.example.demo2.modal.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MemberDao implements IMember {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Member getMemberById(Integer memberId) {
        String sql = "select member_id,password,email,name,age from member where member_id=:memberId";
        Map<String,Object> map = new HashMap<>();
        map.put("memberId",memberId);
        return namedParameterJdbcTemplate.query(sql,map,new MemberRowMapper()).get(0);
    }

    @Override
    public Member getMemberByEmail(String email) {
        String sql = "select member_id,password,email,name,age from member where email=:email";
        Map<String,Object> map = new HashMap<>();
        map.put("email",email);
        return namedParameterJdbcTemplate.query(sql,map,new MemberRowMapper()).get(0);
    }

    @Override
    public Integer createMember(Member member) {
        String sql = "INSERT INTO member(email,password,name,age) VALUES" +
                "(:email,:password,:name,:age)";
        Map<String,Object>map =new HashMap<>();
        map.put("email",member.getEmail());
        map.put("password",member.getPassword());
        map.put("name",member.getEmail());
        map.put("age",member.getAge());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        return namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map),keyHolder);
    }
}
