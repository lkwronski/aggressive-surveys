package com.agh.surveys.service.group;

import com.agh.surveys.model.group.Group;
import com.agh.surveys.model.group.dto.GroupCreateDto;
import com.agh.surveys.model.group.dto.GroupRespDto;
import com.agh.surveys.model.poll.Poll;
import com.agh.surveys.model.poll.dto.PollCreateDto;

public interface IGroupService {


    Integer addGroup(GroupCreateDto groupCreateDto);
    void removeGroup(Group group);
    GroupRespDto getGroupDto(Integer id);
    void removeGroup(Integer id);

    Group getGroup(Integer id);

    void addGroupMember(Integer groupId, String userNick);
    void removeGroupMember(Integer groupId, String userNick);
    Poll addPolltoGroup(PollCreateDto pollCreateDto, Integer groupId);

}
