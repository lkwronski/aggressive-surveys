package com.agh.surveys.service.poll;

import com.agh.surveys.exception.NotFoundException;
import com.agh.surveys.model.group.Group;
import com.agh.surveys.model.poll.ScheduledPoll;
import com.agh.surveys.model.poll.dto.ScheduledPollResponseDto;
import com.agh.surveys.repository.ScheduledPollRepository;
import com.agh.surveys.service.group.GroupService;
import com.agh.surveys.service.group.IntervalParser;
import com.agh.surveys.validation.ScheduledPollValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduledPollService implements IScheduledPollService {

    private static final int ScheduleDeadlineMarginMinutes = 1;

    @Autowired
    ScheduledPollRepository pollRepository;

    @Autowired
    GroupService groupService;

    @Autowired
    ScheduledPollValidator scheduledPollValidator;

    @Autowired
    IntervalParser intervalParser;

    @Override
    public void savePoll(ScheduledPoll poll) {
        pollRepository.save(poll);
    }

    @Override
    public List<ScheduledPoll> getAllScheduledPolls() {
        return pollRepository.findAll();
    }

    @Override
    public List<ScheduledPoll> getAllScheduledPollsForGroup(Integer groupId) {
        Group group = groupService.getGroup(groupId);
        return pollRepository.findAllByPollGroup(group);
    }

    @Override
    public void removeScheduledPollById(Integer pollId) {
        scheduledPollValidator.validateBeforeDelete(pollId);
        pollRepository.deleteById(pollId);
    }

    @Override
    public ScheduledPoll getScheduledPollById(Integer pollId) {
        return pollRepository.findById(pollId).orElseThrow(() -> new NotFoundException("No scheduled poll with such id"));
    }

    @Override
    public ScheduledPollResponseDto scheduledPollToDto(ScheduledPoll poll) {
        String scheduleInterval = intervalParser.fromDurationToString(poll.getScheduleInterval());
        String deadlineInterval = intervalParser.fromDurationToString(poll.getScheduledDeadline());

        return new ScheduledPollResponseDto(poll, scheduleInterval, deadlineInterval);
    }

    @Override
    public ScheduledPollResponseDto getScheduledPollByIdAsDto(Integer id) {
        ScheduledPoll outp = getScheduledPollById(id);

        return scheduledPollToDto(outp);
    }

    @Override
    public List<ScheduledPoll> getPollsToCreate() {
        LocalDateTime now = LocalDateTime.now();
        return pollRepository.findAll().stream()
                .filter(poll ->
                        poll.getPollScheduleTime()
                                .plusHours(poll.getScheduleInterval().toHours())
                                .minusMinutes(ScheduleDeadlineMarginMinutes)
                                .isAfter(now))
                .peek(poll -> {
                    poll.setPollScheduleTime(now);
                    pollRepository.save(poll);
                })
                .collect(Collectors.toList());
    }

}
