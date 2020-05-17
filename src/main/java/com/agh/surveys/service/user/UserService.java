package com.agh.surveys.service.user;

import com.agh.surveys.component.group.PollComponent;
import com.agh.surveys.exception.BusinnessException;
import com.agh.surveys.exception.user.UserExistsInDatabaseException;
import com.agh.surveys.exception.user.UserNotFoundException;
import com.agh.surveys.model.group.Group;
import com.agh.surveys.model.poll.Poll;
import com.agh.surveys.model.user.User;
import com.agh.surveys.model.user.dto.UserDto;
import com.agh.surveys.repository.GroupRepository;
import com.agh.surveys.repository.UserRepository;
import com.agh.surveys.service.group.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    GroupService groupService;

    @Autowired
    PollComponent pollComponent;

    @Override
    public String addUserFromDto(UserDto userDto) {
        User user = new User(userDto);
        if (userRepository.findByUserNick(userDto.getUserNick()).isPresent()) {
            throw new UserExistsInDatabaseException();
        } else {
            return userRepository.save(user).getUserNick();
        }
    }

    @Override
    public void removeUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public void removeUserByNick(String nick) {
        User user = userRepository.getOne(nick);
        if (!user.getManagedGroups().isEmpty()) {
            throw new BusinnessException("This User is a leader of a group and cannot be deleted!");
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

    @Override
    @Transactional
    public List<Poll> getUnfilledPolls(String nick) {
        User user = getUserByNick(nick);
        List<Group> groups = groupRepository.findAll().stream().filter(group -> {
            Group a = groupService.getGroup(group.getId());
            int d = 1;
            return group.getGroupMembers().contains(user);
        }).collect(Collectors.toList());
        return groups.stream().flatMap(group -> group.getGroupPolls().stream().filter(poll -> pollComponent.isUserNotResponseToPoll(user, poll))).collect(Collectors.toList());
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }
}
