package com.example.calendar.service.impl;

import com.example.calendar.entity.Test;
import com.example.calendar.mapper.TestMapper;
import com.example.calendar.service.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    private final TestMapper testMapper;

    public TestServiceImpl(TestMapper testMapper) {
        this.testMapper = testMapper;
    }

    @Override
    public Test selectById(Integer id) {
        return testMapper.selectById(id);
    }
}
