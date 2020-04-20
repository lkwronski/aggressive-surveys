package com.agh.surveys.service;

import com.agh.surveys.model.GroupMember;

import javax.transaction.Transactional;
import java.util.Optional;

public interface IMemberService {

    @Transactional
    void addMember(GroupMember member);

    @Transactional
    void removeMember(GroupMember member);

    @Transactional
    void removeMemberByNick (String nick);

    @Transactional
    void removeMembersFromGroup(Integer id);

    Optional<GroupMember> getGroupsByNick(String nick);
}
