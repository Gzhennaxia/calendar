package com.example.calendar.service.google.impl;

import com.example.calendar.mapper.EventMapper;
import com.example.calendar.service.google.EventsService;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventsServiceImpl implements EventsService {

    private final Calendar service;

    private final EventMapper eventMapper;

    public EventsServiceImpl(Calendar service, EventMapper eventMapper) {
        this.service = service;
        this.eventMapper = eventMapper;
    }

    @Override
    public void getListFormGoogle(String calendarId) throws IOException {
        int maxResults = 10;
        String pageToken = null;
        do {
            Events events = service.events().list(calendarId).setPageToken(pageToken).setMaxResults(maxResults).execute();
            List<Event> items = events.getItems();
            saveEvents(items);
//            for (Event event : items) {
//                System.out.println(event.getSummary());
//            }
            pageToken = events.getNextPageToken();
        } while (pageToken != null);
    }

    @Override
    public void getListFormGoogle(String calendarId, Date timeMin) throws IOException {
        String pageToken = null;
        do {
            Events events = service.events()
                    .list(calendarId)
                    .setPageToken(pageToken)
                    .setTimeMin(new DateTime(timeMin))
                    .execute();
            List<Event> items = events.getItems();
            saveEvents(items);
            pageToken = events.getNextPageToken();
        } while (pageToken != null);
    }

    public void testSave(List<Event> events) {
        saveEvents(events);
    }

    private void saveEvents(List<Event> events) {
        List<com.example.calendar.entity.Event> existedEvents = eventMapper.selectList(events.stream().map(Event::getId).collect(Collectors.toList()));
        List<com.example.calendar.entity.Event> need2InsertedList = new ArrayList<>();
        Iterator<Event> iterator = events.iterator();
        while (iterator.hasNext()) {
            Event next = iterator.next();
            for (com.example.calendar.entity.Event existedEvent : existedEvents) {
                if (next.getId().equals(existedEvent.getEventId())) {
                    iterator.remove();
                    break;
                }
            }
            need2InsertedList.add(new com.example.calendar.entity.Event(next));
        }
        if (!events.isEmpty()) {
            eventMapper.insertList(need2InsertedList);
        }
    }

    @Override
    public List<com.example.calendar.entity.Event> getList(String calendarId) throws IOException {
        String pageToken = null;
        do {
            Events events = service.events().list(calendarId).setPageToken(pageToken).execute();
            List<Event> items = events.getItems();
            for (Event event : items) {
                System.out.println(event.getSummary());
            }
            pageToken = events.getNextPageToken();
        } while (pageToken != null);
//        service.events().list(calendarId);
        return null;
    }
}
