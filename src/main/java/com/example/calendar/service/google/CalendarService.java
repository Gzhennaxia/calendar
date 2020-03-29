package com.example.calendar.service.google;

import com.example.calendar.entity.Calendar;

import java.io.IOException;
import java.util.List;

public interface CalendarService {

    public List<Calendar> getList();

    public List<Calendar> getListFromGoogle() throws IOException;

    public void saveList(List<Calendar> calendars);

}
