package com.agh.surveys.model.group.dto;

import com.agh.surveys.model.group.Group;
import com.agh.surveys.model.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class GroupCreateDto {

    private String groupName;
    private String leaderNick;
    private List<String> groupMembersNicks;

    public GroupCreateDto(String groupName, String groupLeaderNick, List<String> groupMembersNicks){
        this.groupName=groupName;
        this.leaderNick=groupLeaderNick;
        this.groupMembersNicks = groupMembersNicks;
    }
}
