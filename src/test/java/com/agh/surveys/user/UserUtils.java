package com.agh.surveys.user;

import com.agh.surveys.model.user.User;
import com.agh.surveys.model.user.dto.UserDto;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

public class UserUtils {

    public static ResponseEntity<String> createUser(int randomServerPort, UserDto userCreate, TestRestTemplate restTemplate) throws URISyntaxException
    {
        final String baseUrl = "http://localhost:" + randomServerPort + "/users/";
        URI uri = new URI(baseUrl);

        HttpHeaders headers = new HttpHeaders();

        HttpEntity<UserDto> request = new HttpEntity<>(userCreate, headers);

        ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);

        return result;
    }

}
