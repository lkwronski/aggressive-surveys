package com.agh.surveys.service.user;

import com.agh.surveys.model.user.User;
import com.agh.surveys.model.user.dto.UserCreateDto;

public interface IUserService {

    String addUserFromDto(UserCreateDto userCreateDto);
    void removeUser(User user);
    void removeUserByNick(String nick);
    User getUserByNick(String nick);
}
