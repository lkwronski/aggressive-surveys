package com.agh.surveys.group;

import java.net.URISyntaxException;
import java.util.Collections;

import com.agh.surveys.model.group.Group;
import com.agh.surveys.model.group.dto.GroupCreateDto;
import com.agh.surveys.model.user.User;
import com.agh.surveys.model.user.dto.UserDto;
import com.agh.surveys.user.UserUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class GroupSpec {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    int randomServerPort;

    @Test
    public void testAddGroupSuccess() throws URISyntaxException {
        UserDto userCreate =  new UserDto("test", "test", "test", "test@email.com");
        ResponseEntity<String> resultUser = UserUtils.createUser(randomServerPort, userCreate, this.restTemplate);
        GroupCreateDto groupCreateDto = new GroupCreateDto("test", "test", Collections.emptyList());
        ResponseEntity<String> resultGroup = GroupUtils.creatGroup(randomServerPort, groupCreateDto, this.restTemplate);

        //Verify request succeed
        Assert.assertEquals(200, resultUser.getStatusCodeValue());
        Assert.assertEquals(200, resultGroup.getStatusCodeValue());
    }

}