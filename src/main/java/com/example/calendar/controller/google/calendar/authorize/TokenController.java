package com.example.calendar.controller.google.calendar.authorize;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/authorize/token")
public class TokenController {

    @GetMapping("/result")
    public void result(HttpServletRequest request){
        StringBuffer requestURL = request.getRequestURL();
        String queryString = request.getQueryString();
        String fullRequestUrl = requestURL.append("?").append(queryString).toString();
        System.out.println("###### fullRequestUrl ######");
        System.out.println(fullRequestUrl);
    }


    @GetMapping("/result2")
    public void result2(HttpServletRequest request){
        StringBuffer requestURL = request.getRequestURL();
        String queryString = request.getQueryString();
        String fullRequestUrl = requestURL.append("?").append(queryString).toString();
        System.out.println("###### fullRequestUrl ######");
        System.out.println(fullRequestUrl);
    }
}
