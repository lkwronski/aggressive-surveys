package com.agh.surveys.validation;

import com.agh.surveys.exception.BadRequestException;
import com.agh.surveys.model.group.Group;
import com.agh.surveys.model.group.dto.GroupCreateDto;
import com.agh.surveys.model.poll.dto.PollCreateDto;
import com.agh.surveys.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class GroupValidator {

    private static final int pollDeadlineMarginMinutes = 2;

    @Autowired
    CommonValidator commonValidator;

    private LocalDateTime createdPollDeadlineMargin() {
        return LocalDateTime.now().plusMinutes(pollDeadlineMarginMinutes);
    }

    public void validateCreatePollDto(PollCreateDto pollCreateDto, User author, Group group) {
        if (!isDeadlineCorrect(pollCreateDto.getPolDeadline())) {
            throw new BadRequestException(String.format
                    ("Created poll can't have deadline in the past or earlier than %d minutes.", pollDeadlineMarginMinutes));
        }

        if(!isUserMemberOrLeader( author,group)){
            throw new BadRequestException("Poll author must be member of a group");
        }

    }

    public void validateCreateGroupDto(GroupCreateDto groupCreateDto){
        if(commonValidator.isBlank(groupCreateDto.getGroupName())){
            throw new BadRequestException("Group name cannot be blank");
        }
    }

    public void validateBeforeAddingUser(Group group, User user){
        if (isUserMemberOrLeader(user,group)) {
            throw new BadRequestException("This user is already in the group.");
        }
    }

    public void validateBeforeRemovingUser(Group group, User user){
        if(group.getGroupLeader().equals(user)){
            throw new BadRequestException("Leader cannot be removed from a group");
        }

        if (!user.getUserGroups().contains(group)) {
            throw new BadRequestException("This user is not in this group.");
        }
    }

    private boolean isUserMemberOrLeader(User user, Group group) {
        return user.getUserGroups().contains(group) || user.getManagedGroups().contains(group);
    }

    private boolean isDeadlineCorrect(LocalDateTime deadline) {
        return deadline.isBefore(createdPollDeadlineMargin());
    }
}
