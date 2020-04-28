package com.agh.surveys.model.group.dto;

import com.agh.surveys.model.group.Group;
import com.agh.surveys.model.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class GroupRespDto {
    private Integer groupId;
    private String leaderNick;
    private String groupName;
    private List<String> groupMembersNicks;

    public GroupRespDto(Group group){
        this.groupId = group.getId();
        this.leaderNick= group.getGroupLeader().getUserNick();
        this.groupName=group.getGroupName();
        this.groupMembersNicks=group.getGroupMembers()
                .stream()
                .map(User::getUserNick)
                .collect(Collectors.toList());
    }
}
