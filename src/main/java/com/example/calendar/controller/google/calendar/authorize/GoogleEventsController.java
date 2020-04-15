package com.example.calendar.controller.google.calendar.authorize;

import com.example.calendar.annotation.GoogleOAuth;
import com.example.calendar.service.google.EventsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequestMapping("/google/events")
public class GoogleEventsController {

    private final EventsService eventsService;

    public GoogleEventsController(EventsService eventsService) {
        this.eventsService = eventsService;
    }

    @GoogleOAuth
    @GetMapping("/list")
    @ResponseBody
    public String list(@RequestParam("calendarId") String calendarId) throws IOException {
        eventsService.getListFormGoogle(calendarId);
        return "success";
    }

}
