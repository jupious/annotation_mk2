package edu.mit.annotation.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestMapperTests {

    @Autowired
    private TestMapper testMapper;

    @Test
    public void testFindAll(){
        System.out.println(testMapper.findAll());
    }
}
