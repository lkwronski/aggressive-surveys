package com.agh.surveys.model.group.dto;

import lombok.Data;

import java.util.List;

@Data
public class GroupResponseDto {

    private Integer groupId;
    private String groupName;
    private String groupLeaderNick;
    private List<String> groupMembersNicks;
}
