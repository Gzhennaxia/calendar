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

    @GoogleOAuth
    @GetMapping("/list/month")
    @ResponseBody
    public String listMonth(@RequestParam("calendarId") String calendarId) throws IOException {
        eventsService.getLatestMonthList(calendarId);
        return "success";
    }

    @GoogleOAuth
    @GetMapping("/list/week")
    @ResponseBody
    public String listWeek(@RequestParam("calendarId") String calendarId) throws IOException {
        eventsService.getLatestWeekList(calendarId);
        return "success";
    }

    @GoogleOAuth
    @GetMapping("/list/yesterday")
    @ResponseBody
    public String listYesterday(@RequestParam("calendarId") String calendarId) throws IOException {
        eventsService.getYesterdayList(calendarId);
        return "success";
    }

    @GoogleOAuth
    @GetMapping("/list/today")
    @ResponseBody
    public String listToday(@RequestParam("calendarId") String calendarId) throws IOException {
        eventsService.getTodayList(calendarId);
        return "success";
    }

}
