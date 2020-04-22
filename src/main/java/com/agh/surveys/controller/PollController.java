package com.agh.surveys.controller;

import com.agh.surveys.model.poll.Poll;
import com.agh.surveys.model.question.Question;
import com.agh.surveys.model.question.type.QuestionDetails;
import com.agh.surveys.service.poll.PollService;
import com.agh.surveys.service.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("polls")
public class PollController {

    @Autowired
    PollService pollService;

    @Autowired
    QuestionService questionService;

    @GetMapping
    List<Poll> getAllPolls() {
        return pollService.findAll();
    }

    @GetMapping("/{id}")
    Poll findPoll(@PathVariable Long id) {
        return pollService.getPoll(id);
    }

    @DeleteMapping("/{id}")
    void deletePoll(@PathVariable Long id) {
        pollService.deletePoll(id);
    }

    @GetMapping("/{pollId}/questions")
    List<Question> getPollQuestions(@PathVariable Long pollId) {
        return questionService.getByPollId(pollId);
    }

    @PostMapping("/{pollId}/questions")
    Question addQuestion(@PathVariable Long pollId, @RequestBody QuestionDetails questionDetails) {
        return questionService.addQuestion(pollId, questionDetails);
    }
}
