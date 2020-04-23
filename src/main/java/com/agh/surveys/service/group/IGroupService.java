package com.agh.surveys.service.group;

import com.agh.surveys.model.group.Group;
import com.agh.surveys.model.group.dto.GroupDto;
import com.agh.surveys.model.poll.Poll;
import com.agh.surveys.model.poll.dto.PollCreateDto;

public interface IGroupService {


    Integer addGroup(GroupDto groupDto);
    void removeGroup(Group group);
    GroupDto getGroupDto(Integer id);
    void removeGroup(Integer id);

    Group getGroup(Integer id);

    void addGroupMember(Integer groupId, String userNick);
    void removeGroupMember(Integer groupId, String userNick);
    Poll addPolltoGroup(PollCreateDto pollCreateDto, Integer groupId);

}
