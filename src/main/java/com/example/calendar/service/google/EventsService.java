package com.example.calendar.service.google;

import com.example.calendar.entity.Event;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface EventsService {

    public void getListFormGoogle(String calendarId) throws IOException;

    public void getListFormGoogle(String calendarId, Date timeMin) throws IOException;

    public List<Event> getList(String calendarId) throws IOException;

}
