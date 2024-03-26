package com.example.demo2.mapper;

import com.example.demo2.modal.Member;
import com.example.demo2.modal.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberRowMapper implements RowMapper<Member> {

    public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
        Member member = new Member();
        member.setMember_id(rs.getInt("member_id"));
        member.setPassword(rs.getString("password"));
        member.setName(rs.getString("name"));
        member.setEmail(rs.getString("email"));
        member.setAge(rs.getInt("age"));
        return member;
    }
}
