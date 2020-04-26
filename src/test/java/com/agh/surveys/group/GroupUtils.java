package com.agh.surveys.group;

import com.agh.surveys.model.group.Group;
import com.agh.surveys.model.group.dto.GroupCreateDto;
import com.agh.surveys.model.user.User;
import com.agh.surveys.model.user.dto.UserDto;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

public class GroupUtils {

    public static ResponseEntity<String> creatGroup(int randomServerPort, GroupCreateDto groupCreate, TestRestTemplate restTemplate) throws URISyntaxException
    {
        final String baseUrl = "http://localhost:" + randomServerPort + "/groups/";
        URI uri = new URI(baseUrl);

        HttpHeaders headers = new HttpHeaders();

        HttpEntity<GroupCreateDto> request = new HttpEntity<>(groupCreate, headers);

        ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);

        return result;
    }

}

