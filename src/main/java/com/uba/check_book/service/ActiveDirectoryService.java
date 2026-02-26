package com.uba.check_book.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class ActiveDirectoryService {
    @Value("${ad.mock:false}")
    private boolean mockEnabled;

    private final RestTemplate restTemplate ;

    public ActiveDirectoryService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean login(String username, String password) {
        if (mockEnabled) {
            System.out.println("⚠ AD MOCK ENABLED — returning TRUE");
            return true;
        }
        String url = "http://paperless.ubagroup.com/ad.service/api/AD/AuthenticateUser";

        Map<String, String> request = Map.of(
                "username", username,
                "password", password
        );

        try {
            ResponseEntity<Boolean> response =
                    restTemplate.postForEntity(url, request, Boolean.class);

            return Boolean.TRUE.equals(response.getBody());
        } catch (Exception e) {
            throw new RuntimeException("Unable to contact Active Directory service");
        }
    }
}
