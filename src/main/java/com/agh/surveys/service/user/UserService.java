package com.agh.surveys.service.user;

import com.agh.surveys.exception.BadRequestException;
import com.agh.surveys.exception.NotFoundException;
import com.agh.surveys.exception.user.UserExistsInDatabaseException;
import com.agh.surveys.exception.user.UserNotFoundException;
import com.agh.surveys.model.group.Group;
import com.agh.surveys.model.user.User;
import com.agh.surveys.model.user.dto.UserDto;
import com.agh.surveys.repository.UserRepository;
import com.agh.surveys.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserValidator userValidator;

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
}
