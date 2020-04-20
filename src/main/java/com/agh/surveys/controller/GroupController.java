package com.agh.surveys.controller;


import com.agh.surveys.exception.GroupNotFoundException;
import com.agh.surveys.model.Group;
import com.agh.surveys.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("groups")
public class GroupController {

    @Autowired
    GroupService groupService;

    @PostMapping
    public void addGroup(@RequestBody Group group){
        groupService.addGroup(group);
    }

    @DeleteMapping
    public void removeGroup(@RequestBody Group group){
        groupService.removeGroup(group);
    }

    @GetMapping("/{name}")
    public Group getGroup(@PathVariable(value="name") String name){
        return groupService.getGroupByName(name).orElseThrow(GroupNotFoundException::new);
    }

    @DeleteMapping("/{name}")
    public void removeGroupByName(@PathVariable(value = "name") String name){
        groupService.removeGroupByName(name);
    }
}
