package com.example.demo2.dao.Interface;

import com.example.demo2.modal.Member;

public interface IMember {

    Member getMemberById(Integer memberId);
    Member getMemberByEmail(String email);
    Integer createMember(Member member);
}
