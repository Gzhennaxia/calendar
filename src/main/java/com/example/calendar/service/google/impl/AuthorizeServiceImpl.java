package com.example.calendar.service.google.impl;

import com.example.calendar.service.google.AuthorizeService;
import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import com.google.api.client.auth.oauth2.AuthorizationCodeTokenRequest;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.CalendarScopes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Collection;
import java.util.Collections;

@Service
public class AuthorizeServiceImpl implements AuthorizeService {

    private final GoogleClientSecrets googleClientSecrets;

    private final GoogleCredential credential;

    private final AuthorizationCodeFlow authorizationCodeFlow;

    public AuthorizeServiceImpl(GoogleClientSecrets googleClientSecrets, GoogleCredential credential, AuthorizationCodeFlow authorizationCodeFlow) throws IOException {
        this.credential = credential;
        this.googleClientSecrets = googleClientSecrets;
        this.authorizationCodeFlow = authorizationCodeFlow;
    }

    public String getFirstRedirectUrl() {
        return googleClientSecrets.getDetails().getRedirectUris().get(0);
    }

    @Override
    public String acquireToken(String code) {
        AuthorizationCodeTokenRequest authorizationCodeTokenRequest = this.authorizationCodeFlow.newTokenRequest(code);
        authorizationCodeTokenRequest.setRedirectUri(getFirstRedirectUrl());
        try {
            TokenResponse response = authorizationCodeTokenRequest.execute();
            return response.getAccessToken();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean existAccessToken() {
        return !StringUtils.isEmpty(credential.getAccessToken());
    }

    @Override
    public String buildRequestUrl() {
        AuthorizationCodeRequestUrl authorizationCodeRequestUrl = authorizationCodeFlow.newAuthorizationUrl();
        authorizationCodeRequestUrl.setRedirectUri(getFirstRedirectUrl());
        return String.valueOf(authorizationCodeRequestUrl);
    }

    @Override
    public void storeAccessToken(String token) {
        this.credential.setAccessToken(token);
    }
}
