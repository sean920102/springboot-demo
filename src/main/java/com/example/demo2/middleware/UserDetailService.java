package com.example.demo2.middleware;

import com.example.demo2.dao.MemberDao;
import com.example.demo2.modal.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class UserDetailService implements UserDetailsService {
    @Autowired
    private MemberDao memberDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = memberDao.getMemberByEmail(username);

        String account = member.getName();
        String password = member.getPassword();

        return new User(account,password, new ArrayList<>());
    }
}
