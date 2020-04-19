package com.agh.surveys.controller;

import com.agh.surveys.model.User;
import com.agh.surveys.model.poll.question.Poll;
import com.agh.surveys.model.poll.question.Question;
import com.agh.surveys.service.UserService;
import com.agh.surveys.service.poll.PollService;
import com.agh.surveys.service.poll.dto.PollPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("polls")
public class PollController {


    @Autowired
    PollService pollService;

    @GetMapping
    List<Poll> all() {
        return pollService.findAll();
    }

    @PostMapping
    Poll addPoll(@RequestBody PollPost pollPost) {
        return pollService.addPoll(pollPost);
    }

    @GetMapping("/{id}")
    Poll findPoll(@PathVariable Long id) {
        return pollService.findPoll(id);
    }

    @DeleteMapping("/{id}")
    void deletePoll(@PathVariable Long id) {
        pollService.deletePoll(id);
    }

}
