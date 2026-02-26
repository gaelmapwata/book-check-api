package com.uba.check_book.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

@Service
public class EntrustService {

    @Value("${entrust.url}")
    private String entrustUrl;

    @Value("${entrust.username}")
    private String username;

    @Value("${entrust.password}")
    private String password;

    @Value("${entrust.mock:false}")
    private boolean mock;

    private final RestTemplate restTemplate;

    public EntrustService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean sendEntrustToken(String email, String otp) throws Exception {
        if (mock) {
            System.out.println("EntrustService | MOCK mode ON: OTP sent to " + email + " = " + otp);
            return true;
        }

        String usernameWithoutDomain = email.replace("@ubagroup.com", "");

        String xmlBody = String.format("""
<?xml version="1.0" encoding="UTF-8"?>
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ws="http://ws.waei.uba.com/">
  <soapenv:Header/>
  <soapenv:Body>
    <ws:authenticateToken>
      <request>
        <response>%s</response>
        <userGroup></userGroup>
        <username>%s</username>
        <requesterId>?</requesterId>
        <requesterIp>?</requesterIp>
      </request>
    </ws:authenticateToken>
  </soapenv:Body>
</soapenv:Envelope>
""", otp, usernameWithoutDomain);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_XML);
        headers.setBasicAuth(username, password);

        HttpEntity<String> request = new HttpEntity<>(xmlBody, headers);

        ResponseEntity<String> response =
                restTemplate.postForEntity(entrustUrl, request, String.class);

        // TODO: parser le XML pour vérifier isSuccessful === 'true'
        return response.getStatusCode().is2xxSuccessful();
    }
}