package com.agh.surveys.service.group;

import com.agh.surveys.model.group.Group;
import com.agh.surveys.model.group.dto.GroupCreateDto;
import com.agh.surveys.model.group.dto.GroupRespDto;
import com.agh.surveys.model.poll.Poll;
import com.agh.surveys.model.poll.dto.PollCreateDto;
import com.agh.surveys.model.poll.dto.PollResponseDto;

import java.util.List;

public interface IGroupService {


    Integer addGroup(GroupCreateDto groupCreateDto);

    void removeGroup(Group group);

    GroupRespDto getGroupDto(Integer id);

    void removeGroup(Integer id);

    Group getGroup(Integer id);

    void addGroupMember(Integer groupId, String userNick);

    void removeGroupMember(Integer groupId, String userNick);

    Poll addPolltoGroup(PollCreateDto pollCreateDto, Integer groupId);

    List<Poll> getFilledPolls(Integer groupId, String userNick);

    List<Poll> getUnfilledPolls(Integer groupId, String userNick);
}
