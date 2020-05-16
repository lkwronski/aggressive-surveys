package com.agh.surveys.service.poll;

import com.agh.surveys.model.poll.ScheduledPoll;
import com.agh.surveys.model.poll.dto.ScheduledPollResponseDto;

import java.util.List;

public interface IScheduledPollService {
    void savePoll(ScheduledPoll poll);

    List<ScheduledPoll> getAllScheduledPolls();

    List<ScheduledPoll> getAllScheduledPollsForGroup(Integer groupId);

    ScheduledPollResponseDto getScheduledPollByIdAsDto(Integer id);
}
