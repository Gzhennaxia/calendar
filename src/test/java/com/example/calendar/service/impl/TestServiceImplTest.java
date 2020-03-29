package com.example.calendar.service.impl;

import com.example.calendar.service.TestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestServiceImplTest {

    @Autowired
    private TestService testMapper;

    @Test
    void selectById() {
        com.example.calendar.entity.Test test = testMapper.selectById(1);
        System.out.println(test);
    }

}