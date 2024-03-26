package com.example.demo2.service.IService;

import com.example.demo2.modal.Member;

public interface IMemberService {
    Member getMemberById(Integer memberId);
    Member getMemberByEmail(String email);
    Integer createMember(Member member);
}
