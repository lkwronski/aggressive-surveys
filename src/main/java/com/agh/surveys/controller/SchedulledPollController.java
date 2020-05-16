package com.agh.surveys.controller;

import com.agh.surveys.model.poll.dto.PollResponseDto;
import com.agh.surveys.model.poll.dto.ScheduledPollResponseDto;
import com.agh.surveys.service.poll.ScheduledPollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("schedpolls")
@CrossOrigin(origins = {"http://localhost:8100", "http://localhost:8080"})
public class SchedulledPollController {

    @Autowired
    ScheduledPollService scheduledPollService;

    @GetMapping("/{id}")
    ScheduledPollResponseDto findPoll(@PathVariable Integer id) {
        return scheduledPollService.getScheduledPollByIdAsDto(id);
    }

    @DeleteMapping("/{id}")
    void deletePoll(@PathVariable Integer id) {
        scheduledPollService.removeScheduledPollById(id);
    }
}
