package com.agh.surveys.controller;

import com.agh.surveys.model.poll.Poll;
import com.agh.surveys.model.poll.dto.PollResponseDto;
import com.agh.surveys.model.question.Question;
import com.agh.surveys.model.question.dto.QuestionResponse;
import com.agh.surveys.model.question.type.QuestionDetails;
import com.agh.surveys.model.user.dto.UserDto;
import com.agh.surveys.service.poll.PollService;
import com.agh.surveys.service.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("polls")
@CrossOrigin(origins = {"http://localhost:8100","http://localhost:8080"})
public class PollController {

    @Autowired
    PollService pollService;

    @Autowired
    QuestionService questionService;

    @GetMapping
    List<PollResponseDto> getAllPolls() {
        return pollService.findAll().stream().map(PollResponseDto::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    PollResponseDto findPoll(@PathVariable Integer id) {
        return new PollResponseDto(pollService.getPoll(id));
    }

    @DeleteMapping("/{id}")
    void deletePoll(@PathVariable Integer id) {
        pollService.deletePoll(id);
    }

    @GetMapping("/{pollId}/questions")
    List<QuestionResponse> getPollQuestions(@PathVariable Integer pollId) {
        return questionService.getByPollId(pollId).stream().map(QuestionResponse::new).collect(Collectors.toList());
    }

    @GetMapping("/{pollId}/responded_user")
    List<UserDto> getRespondedUser(@PathVariable Integer pollId) {
        return pollService.getRespondedUser(pollId).stream().map(UserDto::new).collect(Collectors.toList());
    }

    @GetMapping("/{pollId}/not_responded_user")
    List<UserDto> getNotRespondedUser(@PathVariable Integer pollId) {
        return pollService.getNotRespondedUser(pollId).stream().map(UserDto::new).collect(Collectors.toList());
    }

    @PostMapping("/{pollId}/questions")
    QuestionResponse addQuestion(@PathVariable Integer pollId, @RequestBody QuestionDetails questionDetails) {
        return new QuestionResponse(questionService.addQuestion(pollId, questionDetails));
    }
}
