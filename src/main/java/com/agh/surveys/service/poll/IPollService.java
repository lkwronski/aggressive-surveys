package com.agh.surveys.service.poll;

import com.agh.surveys.model.poll.Poll;
import com.agh.surveys.model.poll.dto.PollStatisticsDto;
import com.agh.surveys.model.user.User;

import java.util.Arrays;
import java.util.List;

public interface IPollService {

    List<Poll> findAll();

    void deletePoll(Integer id);

    Poll getPoll(Integer id);

    Poll savePoll(Poll poll);

    List<User> getRespondedUser(Integer pollId);

    List<User> getNotRespondedUser(Integer pollId);

    PollStatisticsDto getStatistics(Integer pollId);

    void sendPollNotification(Integer pollId);
}
