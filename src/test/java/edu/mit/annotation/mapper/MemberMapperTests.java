package edu.mit.annotation.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberMapperTests {
    @Autowired
    private MemberMapper memberMapper;

    @Test
    public void testLoginCheck(){
        System.out.println(memberMapper.loginCheck("jupious@gmail.com"));
    }
}
