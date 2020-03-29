package com.example.calendar.controller;

import com.example.calendar.annotation.GoogleOAuth;
import com.example.calendar.service.google.EventsService;
import com.google.api.services.calendar.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequestMapping("/events")
public class EventsController {

    private final EventsService eventsService;

    public EventsController(EventsService eventsService) {
        this.eventsService = eventsService;
    }

    @GoogleOAuth
    @GetMapping("/list")
    @ResponseBody
    public String list(@RequestParam("calendarId") String calendarId) throws IOException {
        eventsService.getList(calendarId);
        return "success";
    }
}
