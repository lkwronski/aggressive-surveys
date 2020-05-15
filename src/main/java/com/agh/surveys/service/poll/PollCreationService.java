package com.agh.surveys.service.poll;

import com.agh.surveys.model.poll.Poll;
import com.agh.surveys.model.poll.dto.PollCreateDto;
import com.agh.surveys.service.group.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class PollCreationService {

    private PollCreationMethod creationMethod;
    private String scheduleInterval;
    private String scheduleDeadline;
    private LocalDateTime creationTime;


    @Autowired
    GroupService groupService;

    public Poll addPolltoGroup(PollCreateDto pollCreateDto, Integer groupId
            , PollCreationMethod creationMethod, String scheduleInterval, String scheduleDeadline, LocalDateTime creationTime) {
        switch(creationMethod){
            case onlyNow:
                return groupService.addPolltoGroup(pollCreateDto,groupId);
            case onlyScheduled:
                return createOnlyScheduled(pollCreateDto,groupId,scheduleInterval,scheduleDeadline,creationTime);
            case nowAndScheduled:
                return createNowAndScheduled(pollCreateDto,groupId,scheduleInterval,scheduleDeadline,creationTime);
        }

    }

    private Poll createNowAndScheduled(PollCreateDto pollCreateDto, Integer groupId, String scheduleInterval, String scheduleDeadline, LocalDateTime creationTime) {




    }

    private Poll createOnlyScheduled(PollCreateDto pollCreateDto, Integer groupId, String scheduleInterval, String scheduleDeadline, LocalDateTime creationTime) {
    }
}
