package com.agh.surveys.model.message.dto;

import com.agh.surveys.model.message.Message;
import com.agh.surveys.model.user.User;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class MessageResponseDto {

    private Integer messageId;
    private Integer groupId;
    private String context;
    private String authorNick;
    private LocalDateTime deadline;
    private List<String> usersAnswered;
    private List<String> usersNotAnswered;

    public MessageResponseDto(Message message) {
        this.messageId = message.getMessageId();
        this.groupId = message.getMessageGroup().getId();
        this.context = message.getMessageContent();
        this.authorNick = message.getMessageAuthor().getUserNick();
        this.deadline = message.getMessageDeadline();
        this.usersAnswered = message.getUsersWhoAnswered()
                .stream()
                .map(User::getUserNick)
                .collect(Collectors.toList());
        this.usersNotAnswered = message.getMessageGroup()
                .getMembersWithAuthor()
                .stream()
                .map(User::getUserNick)
                .filter(nick -> !usersAnswered.contains(nick)
                        && !nick.equals(authorNick))
                .collect(Collectors.toList());
    }
}
