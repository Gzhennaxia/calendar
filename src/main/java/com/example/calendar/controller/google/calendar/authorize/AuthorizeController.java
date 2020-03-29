package com.example.calendar.controller.google.calendar.authorize;

import com.example.calendar.service.google.AuthorizeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/authorize")
@Controller
public class AuthorizeController {

    private final AuthorizeService authorizeService;

    public AuthorizeController(AuthorizeService authorizeService) {
        this.authorizeService = authorizeService;
    }

    @GetMapping("/callback")
    public String callback(String code, String scopes) {
        String accessToken = authorizeService.acquireToken(code);
        authorizeService.storeAccessToken(accessToken);
        return "index";
    }
}
