package com.example.calendar.service.google.impl;

import com.example.calendar.mapper.CalendarMapper;
import com.example.calendar.service.google.CalendarService;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.CalendarList;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CalendarServiceImpl implements CalendarService {

    private final Calendar service;

    private final CalendarMapper calendarMapper;

    public CalendarServiceImpl(Calendar service, CalendarMapper calendarMapper) {
        this.service = service;
        this.calendarMapper = calendarMapper;
    }

    @Override
    public List<com.example.calendar.entity.Calendar> getList() {
        return calendarMapper.selectList();
    }

    @Override
    public List<com.example.calendar.entity.Calendar> getListFromGoogle() throws IOException {
        // Iterate through entries in calendar list
        String pageToken = null;
        CalendarList calendarList;
        do {
            calendarList = this.service.calendarList().list().setPageToken(pageToken).execute();
            pageToken = calendarList.getNextPageToken();
        } while (pageToken != null);
        List<com.example.calendar.entity.Calendar> calendars = com.example.calendar.entity.Calendar.convert2Calendar(calendarList.getItems());
        saveList(calendars);
        return calendars;
    }

    @Override
    public void saveList(List<com.example.calendar.entity.Calendar> calendars) {
        List<com.example.calendar.entity.Calendar> need2SaveCalendars = new ArrayList<>();
        List<com.example.calendar.entity.Calendar> existedCalendars = calendarMapper.selectByList(calendars);
        boolean exist = false;
        for (com.example.calendar.entity.Calendar calendar : calendars) {
            for (com.example.calendar.entity.Calendar existedCalendar : existedCalendars) {
                exist = false;
                if (calendar.getId().equals(existedCalendar.getId())) {
                    exist = true;
                    if (!calendar.equals(existedCalendar)) {
                        need2SaveCalendars.add(calendar);
                    }
                }
            }
            if (!exist) {
                need2SaveCalendars.add(calendar);
            }
        }
        if (!need2SaveCalendars.isEmpty()) {
            calendarMapper.insertList(need2SaveCalendars);
        }
    }
}
