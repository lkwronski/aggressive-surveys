package com.agh.surveys.service.user;

import com.agh.surveys.model.poll.Poll;
import com.agh.surveys.model.user.User;
import com.agh.surveys.model.user.dto.UserDto;

import java.util.Arrays;
import java.util.List;

public interface IUserService {

    String addUserFromDto(UserDto userDto);
    void removeUser(User user);
    void removeUserByNick(String nick);
    User getUserByNick(String nick);

    List<Poll> getUnfilledPolls(String nick);
}
