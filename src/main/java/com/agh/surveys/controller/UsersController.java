package com.agh.surveys.controller;

import com.agh.surveys.model.user.User;
import com.agh.surveys.model.user.dto.UserCreateDto;
import com.agh.surveys.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UsersController {

    @Autowired
    UserService userService;

    @PostMapping
    public String addUser(@RequestBody UserCreateDto userCreateDto) {
        return userService.addUserFromDto(userCreateDto);
    }

    @GetMapping("/{nick}")
    public UserCreateDto getUser(@PathVariable(value = "nick") String nick) {
        User user = userService.getUserByNick(nick);
        return new UserCreateDto(user.getUserNick(), user.getUserFirstName(), user.getUserLastName(), user.getUserEmail());
    }

    @DeleteMapping("/{nick}")
    public void removeUser(@PathVariable(value = "nick") String nick) {
        userService.removeUserByNick(nick);
    }
}
