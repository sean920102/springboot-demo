package com.example.demo2.controller;

import com.example.demo2.modal.Member;
import com.example.demo2.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(
        @PathVariable Integer id
    ){
        Member member = memberService.getMemberById(id);
        return ResponseEntity.status(HttpStatus.OK).body(member);
    }

    @GetMapping()
    public  ResponseEntity<Member> getMemberByEmail(
            @RequestParam @Valid String email
    ){
        Member member = memberService.getMemberByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(member);
    }
}
