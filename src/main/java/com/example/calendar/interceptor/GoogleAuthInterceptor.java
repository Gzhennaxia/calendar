package com.example.calendar.interceptor;

import com.example.calendar.annotation.GoogleOAuth;
import com.example.calendar.service.google.AuthorizeService;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class GoogleAuthInterceptor extends HandlerInterceptorAdapter {

    final AuthorizeService authorizeService;

    public GoogleAuthInterceptor(AuthorizeService authorizeService) {
        this.authorizeService = authorizeService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {

            HandlerMethod handlerMethod = (HandlerMethod) handler;
            GoogleOAuth googleOAuth = handlerMethod.getMethodAnnotation(GoogleOAuth.class);
            if (googleOAuth != null) {
                // 检测是否存在
                boolean existAccessToken = authorizeService.existAccessToken();
                if (!existAccessToken) {
                    String requestUrl = authorizeService.buildRequestUrl();
                    response.sendRedirect(requestUrl);
                    return false;
                }
            }
        }
        return super.preHandle(request, response, handler);
    }
}
