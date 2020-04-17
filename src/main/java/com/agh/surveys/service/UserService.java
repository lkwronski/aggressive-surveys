package com.agh.surveys.service;

import com.agh.surveys.model.User;
import com.agh.surveys.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void removeUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public void removeUserByNick(String nick) {
        userRepository.removeByNick(nick);
    }


    @Override
    public Optional<User> getUserByNick(String nick) {
        return userRepository.findByNick(nick);
    }
}
