package com.agh.surveys.service.user;

import com.agh.surveys.model.message.Message;
import com.agh.surveys.model.poll.Poll;
import com.agh.surveys.model.user.User;
import com.agh.surveys.model.user.dto.UserDto;

import java.util.Arrays;
import java.util.List;

public interface IUserService {

    String addOrEditUserFromDto(UserDto userDto);

    void removeUserByNick(String nick);

    User getUserByNick(String nick);

    List<Message> getUnansweredMessagesBeforeDeadline(String nick);

    List<Poll> getUnfilledPolls(String nick);

    String addUserFromDto(UserDto userDto);
}
