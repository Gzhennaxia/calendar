package com.example.calendar.controller.google.calendar.authorize;

import com.google.api.client.auth.oauth2.AuthorizationCodeResponseUrl;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.DataStore;
import com.google.api.client.util.store.MemoryDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.security.GeneralSecurityException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

//@RequestMapping("/authorize/code")
//@RestController
public class CodeController01 {

    @GetMapping("/acquire")
    public void test() throws IOException {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = requestAttributes.getResponse();

        String clientid = "309035541877-ickjouplif6imld8pp89ejrpgakv9l51.apps.googleusercontent.com";
        String redirectUrl = "http://localhost:8000/authorize/code/result";
        Collection<String> scopes = Collections.singletonList("https://www.googleapis.com/auth/calendar.readonly");
        // https://developers.google.com/api-client-library/java/google-api-java-client/oauth2#authorization_code_flow
        String googleAuthorizationCodeRequestUrl = new GoogleAuthorizationCodeRequestUrl(clientid, redirectUrl, scopes).build();
        System.out.println("####### 重定向链接: googleAuthorizationCodeRequestUrl ######");
        System.out.println(googleAuthorizationCodeRequestUrl);
        response.sendRedirect(googleAuthorizationCodeRequestUrl);
        // ### googleAuthorizationCodeRequestUrl ###
        // https://accounts.google.com/o/oauth2/auth
        // ?client_id=309035541877-ickjouplif6imld8pp89ejrpgakv9l51.apps.googleusercontent.com
        // &redirect_uri=http://localhost:8000/authorize/code/result&response_type=code
        // &scope=https://www.googleapis.com/auth/calendar.readonly
    }

    @GetMapping("/result")
    public void result(HttpServletRequest request) throws GeneralSecurityException, IOException {
        StringBuffer requestURL = request.getRequestURL();
        String queryString = request.getQueryString();
        String fullRequestUrl = requestURL.append("?").append(queryString).toString();
        System.out.println("###### fullRequestUrl ######");
        System.out.println(fullRequestUrl);
        AuthorizationCodeResponseUrl authorizationCodeResponseUrl = new AuthorizationCodeResponseUrl(fullRequestUrl);
        String code = authorizationCodeResponseUrl.getCode();
        if (code != null) {
            System.out.println("###### code 获取成功 ######");
            System.out.println(code);
//            NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            final Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 1087));
            NetHttpTransport HTTP_TRANSPORT = new NetHttpTransport.Builder().setProxy(proxy).build();
            JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
            String clientId = "309035541877-ickjouplif6imld8pp89ejrpgakv9l51.apps.googleusercontent.com";
            String clientSecret = "kJIZe3ek20L6m7H1hpAwnSR8";
//            无法使用别的重定向链接，原因不明
//            String redirectUri = "http://localhost:8000/authorize/token/result";
            String redirectUri = "http://localhost:8000/authorize/code/result";

            GoogleTokenResponse response =
//                    new GoogleAuthorizationCodeTokenRequest(new NetHttpTransport(), new JacksonFactory(),
                    new GoogleAuthorizationCodeTokenRequest(HTTP_TRANSPORT, new JacksonFactory(),
                            clientId, clientSecret,
                            code, redirectUri)
                            .execute();

            String accessToken = response.getAccessToken();
            System.out.println("Access token: " + accessToken);

            Credential googleCredential = new GoogleCredential.Builder()
                    .setClientSecrets(clientId, clientSecret)
                    .build();
            googleCredential.setAccessToken(accessToken);

            MemoryDataStoreFactory memoryDataStoreFactory = MemoryDataStoreFactory.getDefaultInstance();


            String applicationName = "Google Calendar API Java Quickstart";

            // Initialize Calendar service with valid OAuth credentials
            Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, googleCredential)
                    .setApplicationName(applicationName).build();


            // Iterate over the events in the specified calendar
            String pageToken = null;
            do {
                Events events = service.events().list("primary").setPageToken(pageToken).execute();
                List<Event> items = events.getItems();
                for (Event event : items) {
                    System.out.println(event.getSummary());
                }
                pageToken = events.getNextPageToken();
            } while (pageToken != null);
        }
    }
}