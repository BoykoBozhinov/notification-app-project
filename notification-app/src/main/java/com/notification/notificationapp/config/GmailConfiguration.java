package com.notification.notificationapp.config;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.Set;

import static com.google.api.services.gmail.GmailScopes.GMAIL_SEND;

@Configuration
public class GmailConfiguration {

    @Bean
    public Credential getCredentials(NetHttpTransport httpTransport, GsonFactory jsonFactory) throws IOException {
        GoogleClientSecrets clientSecrets =
                GoogleClientSecrets.load(jsonFactory, new InputStreamReader(GmailConfiguration.class.getResourceAsStream("/client_secret_534380842732-r3u0d57sruom3aa0jnvr8752pgfntp04.apps.googleusercontent.com.json")));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                httpTransport, jsonFactory, clientSecrets, Set.of(GMAIL_SEND))
                .setDataStoreFactory(new FileDataStoreFactory(Paths.get("tokens").toFile()))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        //returns an authorized Credential object.
        return  new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    @Bean
    public NetHttpTransport httpTransport() throws GeneralSecurityException, IOException {
        return  GoogleNetHttpTransport.newTrustedTransport();
    }

    @Bean
    public GsonFactory jsonFactory() {
        return GsonFactory.getDefaultInstance();
    }

    @Bean
    public Gmail gmail(NetHttpTransport httpTransport, GsonFactory jsonFactory) throws IOException {
        return new Gmail.Builder(httpTransport, jsonFactory, getCredentials(httpTransport, jsonFactory))
                .setApplicationName("Test Mailer")
                .build();
    }
}
