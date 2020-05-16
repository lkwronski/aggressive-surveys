package com.agh.surveys.service.group;

import com.agh.surveys.model.group.Group;
import com.agh.surveys.model.group.dto.GroupCreateDto;
import com.agh.surveys.model.group.dto.GroupRespDto;
import com.agh.surveys.model.poll.Poll;
import com.agh.surveys.model.poll.ScheduledPoll;
import com.agh.surveys.model.poll.dto.PollCreateDto;
import com.agh.surveys.model.poll.dto.ScheduledPollCreateDto;

import java.util.List;

public interface IGroupService {


    ScheduledPoll addScheduledPollToGroup(ScheduledPollCreateDto pollCreateDto, Integer groupId);

    Poll addPolltoGroup(PollCreateDto pollCreateDto, Integer groupId);

    GroupRespDto addGroup(GroupCreateDto groupCreateDto);

    GroupRespDto getGroupDto(Integer id);

    void removeGroup(Integer id);

    Group getGroup(Integer id);

    void addGroupMember(Integer groupId, String userNick);

    void removeGroupMember(Integer groupId, String userNick);

    void saveGroup(Group group);

    List<Poll> getFilledPolls(Integer groupId, String userNick);

    List<Poll> getUnfilledPolls(Integer groupId, String userNick);

}
