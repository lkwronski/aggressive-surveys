package com.agh.surveys.service;

import com.agh.surveys.model.User;

import java.util.Optional;

public interface IUserService {

    void addUser(User user);
    void removeUser(User user);
    void removeUserByNick(String nick);
    User getUserByNick(String nick);
}
