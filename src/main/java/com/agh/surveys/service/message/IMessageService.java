package com.agh.surveys.service.message;

import com.agh.surveys.model.message.dto.MessageCreateDto;
import com.agh.surveys.model.message.dto.MessageResponseDto;

import java.util.List;

public interface IMessageService {
    MessageResponseDto getMessageById(Integer messageId);

    void removeMessageById(Integer messageId);

    void acknowledgeMessage(Integer messageId, String userNick);

    List<MessageResponseDto> getGroupMessages(Integer groupId);

    MessageResponseDto addMessageToGroup(Integer groupId, MessageCreateDto messageCreateDto);

    List<MessageResponseDto> getGroupMessagesAfterDeadline(Integer groupId);

    List<MessageResponseDto> getGroupMessagesBeforeDeadline(Integer groupId);
}
