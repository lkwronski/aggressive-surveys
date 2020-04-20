package com.agh.surveys.service;


import com.agh.surveys.model.Group;
import com.agh.surveys.repository.IGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GroupService implements IGroupService {

    @Autowired
    IGroupRepository groupRepository;

    @Override
    public void addGroup(Group group) {
        groupRepository.save(group);
    }

    @Override
    public void removeGroup(Group group) {
        groupRepository.delete(group);
    }

    @Override
    public void removeGroupByName(String name) {
        groupRepository.removeByGroupName(name);
    }

    @Override
    public Optional<Group> getGroupByName(String name) {
        return groupRepository.findByGroupName(name);
    }
}
