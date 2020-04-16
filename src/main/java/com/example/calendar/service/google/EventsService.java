package com.example.calendar.service.google;

import com.example.calendar.entity.Event;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface EventsService {

    public void getListFormGoogle(String calendarId) throws IOException;

    public void getLatestMonthList(String calendarId) throws IOException;

    public void getLatestWeekList(String calendarId) throws IOException;

    public void getYesterdayList(String calendarId) throws IOException;

    public void getTodayList(String calendarId) throws IOException;

    public void getListFormGoogle(String calendarId, Date timeMin) throws IOException;

    public List<Event> getList(String calendarId) throws IOException;

}
