package com.agh.surveys.controller;


import com.agh.surveys.model.group.Group;
import com.agh.surveys.model.group.dto.GroupCreateDto;
import com.agh.surveys.model.group.dto.GroupRespDto;
import com.agh.surveys.model.poll.Poll;
import com.agh.surveys.model.poll.dto.PollCreateDto;
import com.agh.surveys.service.group.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("groups")
public class GroupController {

    @Autowired
    GroupService groupService;

    @PostMapping
    public Integer addGroup(@RequestBody GroupCreateDto groupRespDto) {
        return groupService.addGroup(groupRespDto);
    }

    @GetMapping("/{id}")
    public GroupRespDto getGroup(@PathVariable(value = "id") Integer id) {
        return groupService.getGroupDto(id);
    }

    @DeleteMapping("/{id}")
    public void removeGroup(@PathVariable(value = "id") Integer id) {
        groupService.removeGroup(id);
    }

    @GetMapping("/{groupId}/members")
    public List<String> getGroupMembers(@PathVariable(value = "groupId") Integer groupId) {
        return groupService.getGroupDto(groupId).getGroupMembersNicks();
    }

    @PostMapping("/{groupId}/members")
    public void addGroupMember( @PathVariable(value ="groupId")Integer groupId,
                                  @RequestParam(name = "userNick") String userNick ){
         groupService.addGroupMember(groupId,userNick);
    }

    @DeleteMapping("/{groupId}/members/{memberNick}")
    public void removeGroupMember( @PathVariable(value ="groupId")Integer groupId,
                                  @PathVariable(value="memberNick") String userNick ){

         groupService.removeGroupMember(groupId, userNick);
    }

    @GetMapping("/{groupId}/polls")
    public List<Poll> getGroupPolls(@PathVariable(value="groupId") Integer groupId){
        return groupService.getGroup(groupId).getGroupPolls();
    }

    @PostMapping("/{groupId}/polls")
    public Poll addPoll(@PathVariable(value = "groupId") Integer groupId,
                                @RequestBody PollCreateDto pollCreateDto){
        return groupService.addPolltoGroup(pollCreateDto,groupId);
    }


}
