package com.agh.surveys.controller;


import com.agh.surveys.model.group.dto.GroupCreateDto;
import com.agh.surveys.model.group.dto.GroupRespDto;
import com.agh.surveys.model.message.dto.MessageCreateDto;
import com.agh.surveys.model.message.dto.MessageResponseDto;
import com.agh.surveys.model.poll.dto.PollCreateDto;
import com.agh.surveys.model.poll.dto.PollResponseDto;
import com.agh.surveys.model.poll.dto.ScheduledPollCreateDto;
import com.agh.surveys.model.poll.dto.ScheduledPollResponseDto;
import com.agh.surveys.service.group.GroupService;
import com.agh.surveys.service.message.MessageService;
import com.agh.surveys.service.poll.ScheduledPollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("groups")
@CrossOrigin(origins = {"http://localhost:8100", "http://localhost:8080"})
public class GroupController {

    @Autowired
    GroupService groupService;

    @Autowired
    MessageService messageService;

    @Autowired
    ScheduledPollService scheduledPollService;

    @PostMapping
    public GroupRespDto addGroup(@RequestBody GroupCreateDto groupRespDto) {
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
    public void addGroupMember(@PathVariable(value = "groupId") Integer groupId,
                               @RequestParam(name = "userNick") String userNick) {
        groupService.addGroupMember(groupId, userNick);
    }

    @DeleteMapping("/{groupId}/members/{memberNick}")
    public void removeGroupMember(@PathVariable(value = "groupId") Integer groupId,
                                  @PathVariable(value = "memberNick") String userNick) {

        groupService.removeGroupMember(groupId, userNick);
    }

    @GetMapping("/{groupId}/members/{memberNick}/filled_polls")
    public List<PollResponseDto> getFilledPolls(@PathVariable(value = "groupId") Integer groupId,
                                                @PathVariable(value = "memberNick") String userNick) {

        return groupService.getFilledPolls(groupId, userNick).stream().map(PollResponseDto::new).collect(Collectors.toList());
    }

    @GetMapping("/{groupId}/members/{memberNick}/unfilled_polls")
    public List<PollResponseDto> getUnfilledPolls(@PathVariable(value = "groupId") Integer groupId,
                                                  @PathVariable(value = "memberNick") String userNick) {

        return groupService.getUnfilledPolls(groupId, userNick).stream().map(PollResponseDto::new).collect(Collectors.toList());
    }

    @GetMapping("/{groupId}/polls")
    public List<PollResponseDto> getGroupPolls(@PathVariable(value = "groupId") Integer groupId) {
        return groupService.getGroup(groupId).getGroupPolls().stream().map(PollResponseDto::new).collect(Collectors.toList());
    }

    @PostMapping("/{groupId}/polls")
    public PollResponseDto addPoll(@PathVariable(value = "groupId") Integer groupId,
                                   @RequestBody PollCreateDto pollCreateDto) {

        return new PollResponseDto(groupService.addPolltoGroup(pollCreateDto, groupId));
    }

    @PostMapping("/{groupId}/scheduledPolls")
    public ScheduledPollResponseDto addScheduledPoll(@PathVariable(value = "groupId") Integer groupId,
                                                     @RequestBody ScheduledPollCreateDto pollCreateDto) {

        return new ScheduledPollResponseDto(groupService.addScheduledPollToGroup(pollCreateDto, groupId),
                pollCreateDto.getScheduledInterval(), pollCreateDto.getDeadlineInterval());
    }

    @GetMapping("/{groupId}/scheduledPolls")
    public List<ScheduledPollResponseDto> getGroupScheduledPolls(@PathVariable(value = "groupId") Integer groupId) {
        return scheduledPollService.getAllScheduledPollsForGroup(groupId).stream()
                .map(x-> scheduledPollService.scheduledPollToDto(x))
                .collect(Collectors.toList());
    }


    @GetMapping("/{groupId}/messages")
    public List<MessageResponseDto> getGroupMessages(@PathVariable(value = "groupId") Integer groupId) {
        return messageService.getGroupMessages(groupId);
    }

    @GetMapping("/{groupId}/messagesAfterDeadline")
    public List<MessageResponseDto> getGroupMessagesAfterDeadline(@PathVariable(value = "groupId") Integer groupId) {
        return messageService.getGroupMessagesAfterDeadline(groupId);
    }

    @GetMapping("/{groupId}/messagesBeforeDeadline")
    public List<MessageResponseDto> getGroupMessagesBeforeDeadline(@PathVariable(value = "groupId") Integer groupId) {
        return messageService.getGroupMessagesBeforeDeadline(groupId);
    }

    @PostMapping("/{groupId}/messages")
    public MessageResponseDto addMessageToGroup(@PathVariable(value = "groupId") Integer groupId,
                                                @RequestBody MessageCreateDto messageCreateDto) {
        return messageService.addMessageToGroup(groupId, messageCreateDto);
    }

}
