package com.agh.surveys.poll;

import com.agh.surveys.model.group.Group;
import com.agh.surveys.model.group.dto.GroupCreateDto;
import com.agh.surveys.model.poll.dto.PollCreateDto;
import com.agh.surveys.model.user.User;
import com.agh.surveys.model.user.dto.UserDto;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

public class PollUtils {

    public static ResponseEntity<String> creatPoll(int randomServerPort, PollCreateDto pollCreate, Integer groupId, TestRestTemplate restTemplate) throws URISyntaxException
    {
        final String baseUrl = "http://localhost:" + randomServerPort + "/groups/" + groupId + "/polls";
        URI uri = new URI(baseUrl);

        HttpHeaders headers = new HttpHeaders();

        HttpEntity<PollCreateDto> request = new HttpEntity<>(pollCreate, headers);

        ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);

        return result;
    }

}


