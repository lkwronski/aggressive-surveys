package com.agh.surveys.controller;


import com.agh.surveys.model.group.dto.GroupCreateDto;
import com.agh.surveys.model.group.dto.GroupRespDto;
import com.agh.surveys.model.poll.Poll;
import com.agh.surveys.model.poll.dto.PollCreateDto;
import com.agh.surveys.model.poll.dto.PollResponseDto;
import com.agh.surveys.service.group.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("groups")
@CrossOrigin(origins = {"http://localhost:8100","http://localhost:8080"})
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
    public List<PollResponseDto> getGroupPolls(@PathVariable(value="groupId") Integer groupId){
        return groupService.getGroup(groupId).getGroupPolls().stream().map(PollResponseDto::new).collect(Collectors.toList());
    }

    @PostMapping("/{groupId}/polls")
    public PollResponseDto addPoll(@PathVariable(value = "groupId") Integer groupId,
                                   @RequestBody PollCreateDto pollCreateDto){
        return new PollResponseDto(groupService.addPolltoGroup(pollCreateDto,groupId));
    }


}
