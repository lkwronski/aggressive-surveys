package com.agh.surveys.controller;

import com.agh.surveys.model.user.User;
import com.agh.surveys.model.user.dto.UserDto;
import com.agh.surveys.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UsersController {

    @Autowired
    UserService userService;

    @PostMapping
    public String addUser(@RequestBody UserDto userDto) {
        return userService.addUserFromDto(userDto);
    }

    @GetMapping("/{nick}")
    public UserDto getUser(@PathVariable(value = "nick") String nick) {
        User user = userService.getUserByNick(nick);
        return new UserDto(user);
    }

    @DeleteMapping("/{nick}")
    public void removeUser(@PathVariable(value = "nick") String nick) {
        userService.removeUserByNick(nick);
    }
}
