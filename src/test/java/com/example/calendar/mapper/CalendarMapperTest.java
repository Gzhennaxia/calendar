package com.example.calendar.mapper;

import com.example.calendar.entity.Calendar;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.List;

@SpringBootTest
class CalendarMapperTest {

    @Autowired
    private CalendarMapper mapper;

    @Test
    public void selectId(){
        Integer integer = mapper.selectId();
        System.out.println(integer);
    }

    @Test
    public void insertList() {
        Calendar test = new Calendar("2", "test");
        List<Calendar> calendars = Collections.singletonList(test);
        Integer ret = mapper.insertList(calendars);
        Assert.isTrue(ret > 0, "fails");
    }

    @Test
    public void selectList() {
        List<Calendar> calendars = mapper.selectList();
        System.out.println(calendars);
    }
}