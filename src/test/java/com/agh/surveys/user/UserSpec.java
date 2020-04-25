package com.agh.surveys.user;

import java.net.URI;
import java.net.URISyntaxException;

import com.agh.surveys.model.user.User;
import com.agh.surveys.model.user.dto.UserDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class UserSpec {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    int randomServerPort;

    @Test
    public void testAddUserSuccess() throws URISyntaxException
    {
        final String baseUrl = "http://localhost:"+randomServerPort+"/users/";
        URI uri = new URI(baseUrl);
        UserDto userCreate = new UserDto("test", "test", "test", "test@email.com" );
        User user = new User(userCreate);

        HttpHeaders headers = new HttpHeaders();

        HttpEntity<User> request = new HttpEntity<>(user, headers);

        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);

        //Verify request succeed
        Assert.assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    public void testAddUserWithSameNickFailure() throws URISyntaxException
    {
        final String baseUrl = "http://localhost:"+randomServerPort+"/users/";
        URI uri = new URI(baseUrl);
        UserDto userCreate = new UserDto("test", "test", "test", "test@email.com" );
        User user = new User(userCreate);

        HttpHeaders headers = new HttpHeaders();

        HttpEntity<User> request = new HttpEntity<>(user, headers);

        ResponseEntity<String> resultFirstAdd = this.restTemplate.postForEntity(uri, request, String.class);

        userCreate.setUserEmail("test2@gmail.com");
        ResponseEntity<String> resultFirstSecond = this.restTemplate.postForEntity(uri, request, String.class);


        //Verify request succeed
        Assert.assertEquals(200, resultFirstAdd.getStatusCodeValue());
        Assert.assertEquals(403, resultFirstSecond.getStatusCodeValue());
    }



}
