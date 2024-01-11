package edu.mit.annotation.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberServiceTests {
    @Autowired
    private MemberService memberService;

    @Test
    public void testGetMemberData(){
        System.out.println(memberService.getMemberData("jupious@gmail.com"));
    }

}
