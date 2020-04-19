package com.agh.surveys.controller;

import com.agh.surveys.exception.UserNotFoundException;
import com.agh.surveys.model.User;
import com.agh.surveys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UsersController {

    @Autowired
    UserService userService;

    @PostMapping
    public String addUser(@RequestBody User user){

        userService.addUser(user);

        return user.getNick();
    }

    @GetMapping("/{nick}")
    public User getUser(@PathVariable(value="nick") String nick){
      return userService.getUserByNick(nick).orElseThrow( UserNotFoundException::new);

    }

    @DeleteMapping("/{nick}")
    public void removeUser(@PathVariable(value = "nick") String nick){
        userService.removeUserByNick(nick);
    }



}
