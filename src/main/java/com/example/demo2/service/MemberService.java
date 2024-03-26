package com.example.demo2.service;

import com.example.demo2.dao.MemberDao;
import com.example.demo2.modal.Member;
import com.example.demo2.service.IService.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberService implements IMemberService {
    @Autowired
    private MemberDao memberDao;
    @Override
    public Member getMemberById(Integer memberId) {
        return memberDao.getMemberById(memberId);
    }

    @Override
    public Member getMemberByEmail(String email) {
        return memberDao.getMemberByEmail(email);
    }

    @Override
    public Integer createMember(Member member) {
        return memberDao.createMember(member);
    }
}
