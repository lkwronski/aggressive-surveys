package com.agh.surveys.group;

import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

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

    @Test
    public void testCreateGroup() throws URISyntaxException {
        UserDto test1 =  new UserDto("test1", "test", "test", "test1@email.com");
        ResponseEntity<String> resultUserTest1 = UserUtils.createUser(randomServerPort, test1, this.restTemplate);
        UserDto test2 =  new UserDto("test2", "test", "test", "test2@email.com");
        ResponseEntity<String> resultUserTest2 = UserUtils.createUser(randomServerPort, test2, this.restTemplate);
        List<String> members = new LinkedList<>();
        members.add(test1.getUserNick());
        //create group with empty name
        GroupCreateDto groupCreateDto = new GroupCreateDto("", "test", members);
        ResponseEntity<String> resultGroupEmptyName = GroupUtils.creatGroup(randomServerPort, groupCreateDto, this.restTemplate);
        //create group without leader
        GroupCreateDto groupCreateDtoWithoutLeader = new GroupCreateDto("withoutLeader", "", members);
        ResponseEntity<String> resultGroupWithoutLeader = GroupUtils.creatGroup(randomServerPort, groupCreateDtoWithoutLeader, this.restTemplate);
        //create group with leader outside the group
        GroupCreateDto groupCreateDtoWithLeaderOutside = new GroupCreateDto("with leader outside", "test2", members);
        ResponseEntity<String> resultGroupWithLeaderOutside = GroupUtils.creatGroup(randomServerPort, groupCreateDtoWithLeaderOutside, this.restTemplate);
        //create empty group
        GroupCreateDto groupCreateDtoEmptyGroup = new GroupCreateDto("empty group", "test2", Collections.emptyList());
        ResponseEntity<String> resultEmptyGroup = GroupUtils.creatGroup(randomServerPort, groupCreateDtoEmptyGroup, this.restTemplate);

        //Verify request succeed
        Assert.assertEquals(200, resultUserTest1.getStatusCodeValue());
        Assert.assertEquals(200, resultUserTest2.getStatusCodeValue());
        Assert.assertEquals(400, resultGroupEmptyName.getStatusCodeValue());
        Assert.assertEquals(404, resultGroupWithoutLeader.getStatusCodeValue());
        Assert.assertEquals(200, resultGroupWithLeaderOutside.getStatusCodeValue());
        Assert.assertEquals(200, resultEmptyGroup.getStatusCodeValue());
    }

}