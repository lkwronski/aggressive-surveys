package com.agh.surveys.service;


import com.agh.surveys.model.GroupMember;
import com.agh.surveys.repository.IMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService implements IMemberService {

    @Autowired
    IMemberRepository memberRepository;

    @Override
    public void addMember(GroupMember member) {
        memberRepository.save(member);
    }

    @Override
    public void removeMember(GroupMember member) {
        memberRepository.delete(member);
    }

    @Override
    public void removeMemberByNick(String nick) {
        memberRepository.removeByNick(nick);
    }

    @Override
    public void removeMembersFromGroup(Integer id) {
        memberRepository.removeByGroupID(id);
    }

    @Override
    public Optional<GroupMember> getGroupsByNick(String nick) {
        return memberRepository.findByNick(nick);
    }
}
