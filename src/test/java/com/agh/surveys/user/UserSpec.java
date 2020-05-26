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
        UserDto userCreate = new UserDto("test", "test", "test", "test@email.com" );

        ResponseEntity<String> result = UserUtils.createUser(randomServerPort,userCreate,this.restTemplate);

        //Verify request succeed
        Assert.assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    public void testAddUserWithSameNickFailure() throws URISyntaxException
    {
        final String baseUrl = "http://localhost:"+randomServerPort+"/users/";
        URI uri = new URI(baseUrl);
        UserDto userCreate = new UserDto("test2", "test", "test", "test2@email.com" );
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

    @Test
    public void testCreateUser() throws URISyntaxException
    {
        //create user with empty mail
        UserDto userCreateEmptyMail = new UserDto("test", "test", "test", "" );
        ResponseEntity<String> resultEmptyMail = UserUtils.createUser(randomServerPort,userCreateEmptyMail,this.restTemplate);
        //create user with mail without @
        UserDto userCreateEmailWithoutAtSign = new UserDto("test", "test", "test", "test" );
        ResponseEntity<String> resultEmailWithoutAtSign = UserUtils.createUser(randomServerPort,userCreateEmailWithoutAtSign,this.restTemplate);
        //create user with empty nick
        UserDto userCreateEmptyNick = new UserDto("", "test", "test", "test@g.pl" );
        ResponseEntity<String> resultEmptyNick = UserUtils.createUser(randomServerPort,userCreateEmptyNick,this.restTemplate);
        //create user with empty lastname
        UserDto userCreateEmptyLastName = new UserDto("test", "test", "", "test@f.pl" );
        ResponseEntity<String> resultEmptyLastName = UserUtils.createUser(randomServerPort,userCreateEmptyLastName,this.restTemplate);
        //create user with empty firstname
        UserDto userCreateEmptyFirstName = new UserDto("teste", "", "test", "test@ff.pl" );
        ResponseEntity<String> resultEmptyFirstName = UserUtils.createUser(randomServerPort,userCreateEmptyFirstName,this.restTemplate);
        //create two users with the same email
        UserDto userFirstTwinMail = new UserDto("twin", "test", "test", "twin@ff.pl" );
        ResponseEntity<String> resultFirstTwinMail = UserUtils.createUser(randomServerPort,userFirstTwinMail,this.restTemplate);
        UserDto userSecondTwinMail = new UserDto("twin", "test", "test", "twin@ff.pl" );
        ResponseEntity<String> resultSecondTwinMail = UserUtils.createUser(randomServerPort,userSecondTwinMail,this.restTemplate);
        //create two users with the same nick
        UserDto userFirstTwinNick = new UserDto("twinNick", "test", "test", "twin@nick0.pl" );
        ResponseEntity<String> resultFirstTwinNick = UserUtils.createUser(randomServerPort,userFirstTwinNick,this.restTemplate);
        UserDto userSecondTwinNick = new UserDto("twinNick", "test", "test", "twin@nick1.pl" );
        ResponseEntity<String> resultSecondTwinNick = UserUtils.createUser(randomServerPort,userSecondTwinNick,this.restTemplate);

        //Verify request succeed
        Assert.assertEquals(400, resultEmptyMail.getStatusCodeValue());
        Assert.assertEquals(400, resultEmailWithoutAtSign.getStatusCodeValue());
        Assert.assertEquals(200, resultEmptyNick.getStatusCodeValue()); //should be 200?
        Assert.assertEquals(200, resultEmptyLastName.getStatusCodeValue()); //should be 200?
        Assert.assertEquals(200, resultEmptyFirstName.getStatusCodeValue()); //should be 200?
        Assert.assertEquals(200, resultFirstTwinMail.getStatusCodeValue());
        Assert.assertEquals(400, resultSecondTwinMail.getStatusCodeValue());
        Assert.assertEquals(200, resultFirstTwinNick.getStatusCodeValue());
        Assert.assertEquals(400, resultSecondTwinNick.getStatusCodeValue());
    }

}
