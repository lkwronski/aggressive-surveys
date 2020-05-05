package com.agh.surveys.service.user;

import com.agh.surveys.model.user.User;
import com.agh.surveys.model.user.dto.UserDto;

public interface IUserService {

    String addUserFromDto(UserDto userDto);
    void removeUserByNick(String nick);
    User getUserByNick(String nick);
}
