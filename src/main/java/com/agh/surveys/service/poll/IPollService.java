package com.agh.surveys.service.poll;

import com.agh.surveys.model.poll.Poll;
import com.agh.surveys.service.poll.dto.PollCreate;

import java.util.List;

public interface IPollService {

    List<Poll> findAll();

    void deletePoll(Long id);

    Poll getPoll(Long id);

    Poll addPoll(PollCreate pollCreate);

    void savePoll(Poll poll);
}
