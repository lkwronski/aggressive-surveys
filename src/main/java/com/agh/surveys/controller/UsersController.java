package com.agh.surveys.controller;

import com.agh.surveys.model.group.dto.GroupRespDto;
import com.agh.surveys.model.message.dto.MessageResponseDto;
import com.agh.surveys.model.user.User;
import com.agh.surveys.model.user.dto.UserDto;
import com.agh.surveys.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("users")
@CrossOrigin(origins = {"http://localhost:8100","http://localhost:8080"})
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
        return new UserDto(user.getUserNick(), user.getUserFirstName(), user.getUserLastName(), user.getUserEmail());
    }

    @DeleteMapping("/{nick}")
    public void removeUser(@PathVariable(value = "nick") String nick) {
        userService.removeUserByNick(nick);
    }

    @GetMapping("/{nick}/groups")
    public List<GroupRespDto> getUserGroups(@PathVariable(value = "nick") String nick) {
        return userService.getUserByNick(nick)
                .getUserGroups()
                .stream()
                .map(GroupRespDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{nick}/managedGroups")
    public List<GroupRespDto> getUserManagedGroups(@PathVariable(value = "nick") String nick) {
        return userService.getUserByNick(nick)
                .getManagedGroups()
                .stream()
                .map(GroupRespDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{nick}/unAnsweredMessages")
    public List<MessageResponseDto> getUserUnAnsweredMessagesBeforeDeadline(@PathVariable(value ="nick") String nick){
        return userService.getUnansweredMessagesBeforeDeadline(nick)
                .stream()
                .map(MessageResponseDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{nick}/answeredMessages")
    public List<MessageResponseDto> getUserAnsweredMessages(@PathVariable(value ="nick") String nick){
        return userService.getUserByNick(nick)
                .getAnsweredMessages()
                .stream()
                .map(MessageResponseDto::new)
                .collect(Collectors.toList());
    }
}
