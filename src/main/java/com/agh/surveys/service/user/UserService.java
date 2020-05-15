package com.agh.surveys.service.user;

import com.agh.surveys.exception.NotFoundException;
import com.agh.surveys.exception.user.UserExistsInDatabaseException;
import com.agh.surveys.exception.user.UserNotFoundException;
import com.agh.surveys.model.group.Group;
import com.agh.surveys.model.group.dto.GroupRespDto;
import com.agh.surveys.model.message.Message;

import com.agh.surveys.component.group.GroupComponent;

import com.agh.surveys.model.poll.Poll;
import com.agh.surveys.model.user.User;
import com.agh.surveys.model.user.dto.UserDto;
import com.agh.surveys.repository.GroupRepository;
import com.agh.surveys.repository.UserRepository;
import com.agh.surveys.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserValidator userValidator;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    GroupComponent groupComponent;

    @Override
    public String addUserFromDto(UserDto userDto) {
        userValidator.validateNewUserDto(userDto);
        User user = new User(userDto);
        return userRepository.save(user).getUserNick();
    }

    @Override
    public void removeUserByNick(String nick) {
        User user = userRepository.findByUserNick(nick)
                .orElseThrow(() -> new NotFoundException("Cannot find user with given nick"));

        userValidator.validateBeforeDeletion(user);

        user.getUserGroups()
                .forEach(group -> group.getGroupMembers().remove(user));

        userRepository.removeByUserNick(nick);
    }

    @Override
    public User getUserByNick(String nick) {
        return userRepository.findByUserNick(nick)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public List<Message> getUnansweredMessagesBeforeDeadline(String nick) {
        User user = getUserByNick(nick);
        return user.getAllGroups().stream()
                .flatMap(group -> group.getGroupMessages().stream())
                .filter(message -> !user.getAnsweredMessages().contains(message) && !message.isAfterDeadline())
                .collect(Collectors.toList());
    }
    public List<Poll> getUnfilledPolls(String nick) {
        User user = getUserByNick(nick);
        List<Group> groups = groupRepository.findAll().stream().filter(group -> group.getGroupMembers().contains(user)).collect(Collectors.toList());
        return groups.stream().flatMap(group -> group.getGroupPolls().stream().filter(poll -> groupComponent.userNotResponseToPoll(user, poll))).collect(Collectors.toList());


    }
}
