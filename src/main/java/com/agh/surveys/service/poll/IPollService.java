package com.agh.surveys.service.poll;

import com.agh.surveys.model.poll.Poll;
import com.agh.surveys.model.poll.dto.PollCreateDto;

import java.util.List;

public interface IPollService {

    List<Poll> findAll();

    void deletePoll(Integer id);

    Poll getPoll(Integer id);

    void savePoll(Poll poll);
}
