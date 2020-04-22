package com.agh.surveys.service.poll;

import com.agh.surveys.model.poll.Poll;
import com.agh.surveys.model.poll.dto.PollCreateDto;

import java.util.List;

public interface IPollService {

    List<Poll> findAll();

    void deletePoll(Long id);

    Poll getPoll(Long id);

    Poll addPoll(PollCreateDto pollCreateDto);

    void savePoll(Poll poll);
}
