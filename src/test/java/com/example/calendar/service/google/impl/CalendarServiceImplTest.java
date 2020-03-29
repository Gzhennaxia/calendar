package com.example.calendar.service.google.impl;

import com.example.calendar.entity.Calendar;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CalendarServiceImplTest {

    @Autowired
    private CalendarServiceImpl service;

    @Test
    public void saveList() {
        Calendar test = new Calendar("3", "test");
        Calendar test2 = new Calendar("4", "test");
        List<Calendar> calendars = new java.util.ArrayList<>();
        calendars.add(test);
        calendars.add(test2);
        service.saveList(calendars);
    }

}