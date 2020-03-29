package com.example.calendar.service.google.impl;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;

@SpringBootTest
class EventsServiceImplTest {

    @Autowired
    EventsServiceImpl eventsService;

    @Test
    public void testSave() {
        Event event = new Event();
        event.setId("12345");
        event.setSummary("test");
        event.setDescription("test");
        event.setHtmlLink("test");
        DateTime dateTime = new DateTime(new Date());
        event.setCreated(dateTime);
        event.setUpdated(dateTime);
        event.setStart(new EventDateTime().setDateTime(dateTime));
        event.setEnd(new EventDateTime().setDateTime(dateTime));
        ArrayList<Event> events = new ArrayList<>();
        events.add(event);
        eventsService.testSave(events);
    }

}