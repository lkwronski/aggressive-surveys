package com.agh.surveys.validation;

import com.agh.surveys.exception.BadRequestException;
import com.agh.surveys.exception.NotFoundException;
import com.agh.surveys.model.group.Group;
import com.agh.surveys.model.message.Message;
import com.agh.surveys.model.message.dto.MessageCreateDto;
import com.agh.surveys.model.user.User;
import com.agh.surveys.repository.MessageRepository;
import com.agh.surveys.service.group.IGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MessageValidator {

    @Autowired
    MessageRepository repository;

    @Autowired
    IGroupService groupService;

    @Autowired
    CommonValidator commonValidator;

    private static final int messageDeadlineMinutes = 2;
    private static final int ackDeadline = 0;

    public void validateAcknowledgment(Message message, String userNick) {
        if (message.getMessageAuthor().getUserNick().equals(userNick)) {
            throw new BadRequestException("Author cannot acknowledge his own message");
        }
        if(isDeadlineIncorrect(LocalDateTime.now(),ackDeadline)){
            throw new BadRequestException("Cannot acknowledge after message deadline");
        }

        if (message.getUsersWhoAnswered().stream()
                .map(User::getUserNick)
                .anyMatch(nick -> nick.equals(userNick))) {
            throw new BadRequestException("This member already acknowledge this message");
        }
    }

    public void validateCreatingMessage(MessageCreateDto messageCreateDto, Group group) {
        if (isDeadlineIncorrect(messageCreateDto.getDeadline(),messageDeadlineMinutes)) {
            throw new BadRequestException(
                    "Created message can't have deadline in the past or earlier than few minutes");
        }

        if (group.getMembersWithAuthor()
                .stream()
                .noneMatch(user -> user.getUserNick().equals(messageCreateDto.getAuthorNick()))) {
            throw new BadRequestException("Author is not a member of a group");
        }

    }

    public void checkExistence(Integer messageId) {
        if (!repository.existsById(messageId)) {
            throw new NotFoundException("Not message with such id found");
        }
    }

    private boolean isDeadlineIncorrect(LocalDateTime deadline, int minutes) {
        return deadline.isBefore(commonValidator.createdPollDeadlineMargin(minutes));
    }

}
