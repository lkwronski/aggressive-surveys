package com.agh.surveys.service;

import com.agh.surveys.model.Group;

import java.util.Optional;

public interface IGroupService {

    void addGroup(Group group);
    void removeGroup(Group group);
    void removeGroupByName(String name);
    Optional<Group> getGroupByName (String name);

}
