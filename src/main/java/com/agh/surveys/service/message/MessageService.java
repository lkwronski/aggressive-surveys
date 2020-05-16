package com.agh.surveys.service.message;

import com.agh.surveys.exception.NotFoundException;
import com.agh.surveys.model.group.Group;
import com.agh.surveys.model.message.Message;
import com.agh.surveys.model.message.dto.MessageCreateDto;
import com.agh.surveys.model.message.dto.MessageResponseDto;
import com.agh.surveys.model.user.User;
import com.agh.surveys.repository.MessageRepository;
import com.agh.surveys.service.group.GroupService;
import com.agh.surveys.service.user.UserService;
import com.agh.surveys.validation.MessageValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class MessageService implements IMessageService {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    MessageValidator messageValidator;

    @Autowired
    GroupService groupService;

    @Autowired
    UserService userService;

    @Override
    public MessageResponseDto getMessageById(Integer messageId) {
        messageValidator.checkExistence(messageId);
        return new MessageResponseDto(messageRepository.findById(messageId).orElseThrow(()->new NotFoundException("No message with such id")));
    }

    @Override
    public void removeMessageById(Integer messageId) {
        messageValidator.checkExistence(messageId);
        messageRepository.deleteById(messageId);
    }

    @Override
    public void acknowledgeMessage(Integer messageId, String userNick) {
        messageValidator.checkExistence(messageId);

        Message message = messageRepository.findById(messageId).orElseThrow(() -> new NotFoundException("No message with such id"));
        messageValidator.validateAcknowledgment(message, userNick);

        User user = userService.getUserByNick(userNick);
        message.getUsersWhoAnswered().add(user);
        messageRepository.save(message);

    }

    @Override
    public List<MessageResponseDto> getGroupMessages(Integer groupId) {
        return getFilteredMessages(groupId, (x -> true));
    }

    @Override
    public MessageResponseDto addMessageToGroup(Integer groupId, MessageCreateDto messageCreateDto) {
        Group group = groupService.getGroup(groupId);
        messageValidator.validateCreatingMessage(messageCreateDto, group);

        User author = userService.getUserByNick(messageCreateDto.getAuthorNick());

        Message message = new Message(author, messageCreateDto.getContext(), group, messageCreateDto.getDeadline());
        messageRepository.save(message);
        groupService.saveGroup(group);

        return new MessageResponseDto(message);
    }

    @Override
    public List<MessageResponseDto> getGroupMessagesAfterDeadline(Integer groupId) {
        return getFilteredMessages(groupId, (Message::isAfterDeadline));

    }

    @Override
    public List<MessageResponseDto> getGroupMessagesBeforeDeadline(Integer groupId) {
        return getFilteredMessages(groupId, (x -> !x.isAfterDeadline()));

    }

    private List<MessageResponseDto> getFilteredMessages(Integer groupId, Predicate<? super Message> predicate) {
        return groupService.getGroup(groupId).getGroupMessages()
                .stream()
                .filter(predicate)
                .map(MessageResponseDto::new)
                .collect(Collectors.toList());

    }

}
