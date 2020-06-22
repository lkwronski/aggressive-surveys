package com.agh.surveys.controller;

import com.agh.surveys.model.answer.dto.AnswerResponse;
import com.agh.surveys.model.answer.dto.AnswersRequestDto;
import com.agh.surveys.model.poll.dto.PollResponseDto;
import com.agh.surveys.model.poll.dto.PollStatisticsDto;
import com.agh.surveys.model.question.dto.QuestionCreateDto;
import com.agh.surveys.model.question.dto.QuestionResponse;
import com.agh.surveys.model.question.type.QuestionDetails;
import com.agh.surveys.model.user.dto.UserDto;
import com.agh.surveys.service.answer.AnswerService;
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

    @Autowired
    AnswerService answerService;

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

    @GetMapping("/{pollId}/notification")
    void sendPollNotification(@PathVariable Integer pollId){
        pollService.sendPollNotification(pollId);
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
    QuestionResponse addQuestion(@PathVariable Integer pollId, @RequestBody QuestionCreateDto questionDetails) {
        return new QuestionResponse(questionService.addQuestion(pollId, questionDetails));
    }

    @PostMapping("/{pollId}/answers")
    List<AnswerResponse> addAnswersToPoll(@PathVariable Integer pollId, @RequestBody AnswersRequestDto answersRequestDto) {
        return answerService.answerQuestion(pollId, answersRequestDto);
    }

    @PostMapping("/{pollId}/statistics")
    PollStatisticsDto getStatistics(@PathVariable Integer pollId ){
        return pollService.getStatistics(pollId);
    }

}
