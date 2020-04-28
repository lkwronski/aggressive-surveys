package com.agh.surveys.service.user;

import com.agh.surveys.exception.BusinessException;
import com.agh.surveys.exception.user.UserExistsInDatabaseException;
import com.agh.surveys.exception.user.UserNotFoundException;
import com.agh.surveys.model.group.Group;
import com.agh.surveys.model.user.User;
import com.agh.surveys.model.user.dto.UserDto;
import com.agh.surveys.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public String addUserFromDto(UserDto userDto) {
        validateUser(userDto);

        User user = new User(userDto);
        return userRepository.save(user).getUserNick();
    }

    private void validateUser(UserDto userDto) {
        if (isUserEmailInvalid(userDto.getUserEmail())) {
            throw new BusinessException("Email is already taken!");
        }
        if (isUserNickInvalid(userDto.getUserNick())) {
            throw new UserExistsInDatabaseException();
        }
    }

    private boolean isUserEmailInvalid(String email) {
        return userRepository.findByUserEmail(email).isPresent();
    }

    private boolean isUserNickInvalid(String nick) {
        return userRepository.findByUserNick(nick).isPresent();
    }

    @Override
    public void removeUser(User user) {
        userRepository.delete(user);
    }

    public boolean isUserInGroup(User user, Group group) {
        return user.getManagedGroups().contains(group) ||
                user.getUserGroups().contains(group);
    }

    @Override
    public void removeUserByNick(String nick) {
        User user = userRepository.findByUserNick(nick)
                .orElseThrow(UserNotFoundException::new);

        if (!user.getManagedGroups().isEmpty()) {
            throw new BusinessException("This User is a leader of a group and cannot be deleted!");
        }
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
