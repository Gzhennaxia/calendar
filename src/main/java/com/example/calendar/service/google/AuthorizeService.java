package com.example.calendar.service.google;

public interface AuthorizeService {

    public String acquireToken(String code);

    public boolean existAccessToken();

    public String buildRequestUrl();

    void storeAccessToken(String accessToken);
}
