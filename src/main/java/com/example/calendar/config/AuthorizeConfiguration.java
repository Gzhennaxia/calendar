package com.example.calendar.config;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Collection;
import java.util.Collections;

@Configuration
public class AuthorizeConfiguration {

    @Bean
    public JsonFactory jsonFactory() {
        return new JacksonFactory();
    }

    @Bean
    public HttpTransport transport() throws IOException {
        NetHttpTransport transport = new NetHttpTransport.Builder().build();

        HttpRequestFactory requestFactory = transport.createRequestFactory();
        GenericUrl googleUrl = new GenericUrl("https://www.google.com/");

        HttpRequest request = requestFactory.buildGetRequest(googleUrl);
        HttpResponse response = request.execute();
        if (response.isSuccessStatusCode()) {
            return transport;
        } else {
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 1087));
            transport = new NetHttpTransport.Builder().setProxy(proxy).build();
            requestFactory = transport.createRequestFactory();
            request = requestFactory.buildGetRequest(googleUrl);
            response = request.execute();
            if (response.isSuccessStatusCode()) {
                return transport;
            } else {
                throw new IOException("Cannot access google, check vpn.");
            }
        }
    }

    @Bean
    public GoogleClientSecrets googleClientSecrets(JsonFactory jsonFactory) throws IOException {
        String CREDENTIALS_FILE_PATH = "/google/calendar/authorize/credentials.json";
        InputStream in = this.getClass().getResourceAsStream(CREDENTIALS_FILE_PATH);
        return GoogleClientSecrets.load(jsonFactory, new InputStreamReader(in));
    }


    @Bean
    public GoogleCredential googleCredential() {
        return new GoogleCredential();
    }

    @Bean
    public AuthorizationCodeFlow authorizationCodeFlow(HttpTransport transport, JsonFactory jsonFactory, GoogleClientSecrets clientSecrets) {
        Collection<String> scopes = Collections.singletonList(CalendarScopes.CALENDAR_READONLY);
        return new GoogleAuthorizationCodeFlow.Builder(transport, jsonFactory, clientSecrets, scopes).build();
    }

    @Bean
    public Calendar calendar(HttpTransport transport, JsonFactory jsonFactory, Credential credential) {
        return new Calendar.Builder(transport, jsonFactory, credential).build();
    }
}
