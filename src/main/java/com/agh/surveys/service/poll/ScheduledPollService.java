package com.agh.surveys.service.poll;

import com.agh.surveys.model.poll.dto.ScheduledPollResponseDto;
import com.agh.surveys.service.group.IntervalParser;
import com.agh.surveys.validation.ScheduledPollValidator;
import com.agh.surveys.exception.NotFoundException;
import com.agh.surveys.model.group.Group;
import com.agh.surveys.model.poll.ScheduledPoll;
import com.agh.surveys.repository.ScheduledPollRepository;
import com.agh.surveys.service.group.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduledPollService implements  IScheduledPollService {


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
    public List<ScheduledPoll> getAllScheduledPolls(){
        return pollRepository.findAll();
    }

    @Override
    public List<ScheduledPoll> getAllScheduledPollsForGroup(Integer groupId){
        Group group = groupService.getGroup(groupId);
        return pollRepository.findAllByPollGroup(group);
    }

    public void removeScheduledPollById(Integer pollId){
        scheduledPollValidator.validateBeforeDelete(pollId);
        pollRepository.deleteById(pollId);
    }

    public ScheduledPoll getScheduledPollById(Integer pollId){
        return pollRepository.findById(pollId).orElseThrow(() -> new NotFoundException("No scheduled poll with such id"));
    }

    public ScheduledPollResponseDto scheduledPollToDto(ScheduledPoll poll){
        String scheduleInterval = intervalParser.fromDurationToString(poll.getScheduleInterval());
        String deadlineInterval = intervalParser.fromDurationToString(poll.getScheduledDeadline());

        return new ScheduledPollResponseDto(poll,scheduleInterval,deadlineInterval);
    }

    @Override
    public ScheduledPollResponseDto getScheduledPollByIdAsDto(Integer id) {
        ScheduledPoll outp = getScheduledPollById(id);

        return scheduledPollToDto(outp);
    }

}
