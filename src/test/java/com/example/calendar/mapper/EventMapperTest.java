package com.example.calendar.mapper;

import com.example.calendar.entity.Event;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
class EventMapperTest {

    @Autowired
    private EventMapper mapper;

    @Test
    public void insertList() {
        Event event = new Event();
        event.setEventId("12345");
        event.setTitle("test");
        ArrayList<Event> events = new ArrayList<>();
        events.add(event);
        Integer ret = mapper.insertList(events);

    }

}