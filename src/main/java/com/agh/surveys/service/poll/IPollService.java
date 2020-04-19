package com.agh.surveys.service.poll;

import com.agh.surveys.model.User;
import com.agh.surveys.model.poll.question.Poll;
import com.agh.surveys.model.poll.question.Question;
import com.agh.surveys.service.poll.dto.PollPost;

import java.time.LocalDateTime;
import java.util.List;

public interface IPollService {

    List<Poll> findAll();

    void deletePoll(Long id);

    Poll findPoll(Long id);

    Poll addPoll(PollPost pollPost);
}
