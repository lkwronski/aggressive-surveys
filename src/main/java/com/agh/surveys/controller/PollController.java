package com.agh.surveys.controller;

import com.agh.surveys.model.poll.Poll;
import com.agh.surveys.service.poll.PollService;
import com.agh.surveys.service.poll.dto.PollCreate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    Poll addPoll(@RequestBody PollCreate pollCreate) {
        return pollService.addPoll(pollCreate);
    }

    @GetMapping("/{id}")
    Poll findPoll(@PathVariable Long id) {
        return pollService.getPoll(id);
    }

    @DeleteMapping("/{id}")
    void deletePoll(@PathVariable Long id) {
        pollService.deletePoll(id);
    }

}
