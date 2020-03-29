package com.example.calendar.controller.google.calendar.authorize;

import com.example.calendar.annotation.GoogleOAuth;
import com.example.calendar.service.google.CalendarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@RequestMapping("/calendar")
@Controller
public class CalendarController {

    private final CalendarService calendarService;

    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @GetMapping("/list")
    public String list(ModelMap map) {
        map.addAttribute("list", calendarService.getList());
        return "calendar/list";
    }

    @GoogleOAuth
    @GetMapping("/google/list")
    public String listFromGoogle(ModelMap map) throws IOException {
        map.addAttribute("list", calendarService.getListFromGoogle());
        return "calendar/list";
    }

    //    @GoogleOAuth
//    @GetMapping("/list")
//    public String list(ModelMap map) throws IOException {
//        List<String> calendarNameList = new ArrayList<>();
//        calendarNameList.add("aaa");
//        calendarNameList.add("bbb");
//        calendarNameList.add("ccc");
//        map.addAttribute("list", calendarNameList);
//        return "calendar/list";
//    }
}
