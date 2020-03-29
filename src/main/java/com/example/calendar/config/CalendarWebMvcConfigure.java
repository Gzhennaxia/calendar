package com.example.calendar.config;

import com.example.calendar.interceptor.GoogleAuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CalendarWebMvcConfigure implements WebMvcConfigurer {

    private final GoogleAuthInterceptor googleAuthInterceptor;

    public CalendarWebMvcConfigure(GoogleAuthInterceptor googleAuthInterceptor) {
        this.googleAuthInterceptor = googleAuthInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(googleAuthInterceptor);
    }

}
