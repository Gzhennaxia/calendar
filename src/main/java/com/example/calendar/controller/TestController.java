package com.example.calendar.controller;

import com.example.calendar.annotation.GoogleOAuth;
import com.example.calendar.service.google.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Date;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private EventsService eventsService;

    @ResponseBody
    @GetMapping("")
    public String test() {
        return "success";
    }

    @GoogleOAuth
    @ResponseBody
    @GetMapping("/getListFormGoogle")
    public String testGetListFormGoogle() throws IOException {
        eventsService.getListFormGoogle("primary", new Date());
        return "success";
    }
}
