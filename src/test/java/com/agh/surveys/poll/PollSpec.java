package com.agh.surveys.poll;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.agh.surveys.group.GroupUtils;
import com.agh.surveys.model.group.dto.GroupCreateDto;
import com.agh.surveys.model.poll.Poll;
import com.agh.surveys.model.poll.dto.PollCreateDto;
import com.agh.surveys.model.question.Question;
import com.agh.surveys.model.question.type.QuestionDetails;
import com.agh.surveys.model.question.type.QuestionText;
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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class PollSpec {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    int randomServerPort;

    @Test
    public void testAddPollSuccess() throws URISyntaxException
    {
        UserDto userCreate =  new UserDto("test", "test", "test", "test@email.com");
        ResponseEntity<String> resultUser = UserUtils.createUser(randomServerPort, userCreate, this.restTemplate);
        GroupCreateDto groupCreateDto = new GroupCreateDto("test", "test", Collections.emptyList());
        ResponseEntity<String> resultGroup = GroupUtils.creatGroup(randomServerPort, groupCreateDto, this.restTemplate);

        List<QuestionDetails> questions = new ArrayList<>();
        questions.add(new QuestionText("test"));
        questions.add(new QuestionText("test2"));
        PollCreateDto pollCreate = new PollCreateDto("test", LocalDateTime.now(), "test", questions);
        ResponseEntity<String> resultPoll = PollUtils.creatPoll(randomServerPort, pollCreate, 1, this.restTemplate); // trzeba wyciagnac id z grupy


        //Verify request succeed
        Assert.assertEquals(200, resultUser.getStatusCodeValue());
        Assert.assertEquals(200, resultGroup.getStatusCodeValue());
        Assert.assertEquals(200, resultPoll.getStatusCodeValue());

        // listPollFromGroupSuccess
        String listPoll = "http://localhost:" + randomServerPort + "/groups/" + 1 + "/polls";

        ResponseEntity<Poll[]> result = restTemplate.getForEntity(listPoll, Poll[].class);

        Assert.assertTrue(result.getBody().length  == 1);

        // remove question

        String deleteQuestion = "http://localhost:" + randomServerPort + "/questions/" + 2;

        ResponseEntity<Integer> resultDeleteQuestions = restTemplate.exchange(deleteQuestion,
                HttpMethod.DELETE,
                HttpEntity.EMPTY,
                Integer.class
        );

        Assert.assertEquals(200, resultDeleteQuestions.getStatusCodeValue());

    }

    @Test
    public void listPollFromGroupSuccess() throws URISyntaxException {

    }


}
