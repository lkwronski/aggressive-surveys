package com.agh.surveys.service.poll;

import com.agh.surveys.model.poll.ScheduledPoll;
import com.agh.surveys.model.poll.dto.ScheduledPollResponseDto;

import java.util.List;

public interface IScheduledPollService {
    void savePoll(ScheduledPoll poll);

    List<ScheduledPoll> getAllScheduledPolls();

    List<ScheduledPoll> getAllScheduledPollsForGroup(Integer groupId);

    void removeScheduledPollById(Integer pollId);

    ScheduledPoll getScheduledPollById(Integer pollId);

    ScheduledPollResponseDto scheduledPollToDto(ScheduledPoll poll);

    ScheduledPollResponseDto getScheduledPollByIdAsDto(Integer id);

    List<ScheduledPoll> getPollsToCreate();
}
