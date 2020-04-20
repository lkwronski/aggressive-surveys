package com.agh.surveys.controller;


import com.agh.surveys.exception.UserNotFoundException;
import com.agh.surveys.model.GroupMember;
import com.agh.surveys.service.MemberService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("members")
public class MemberController {

    MemberService memberService;

    @PostMapping
    public void addMember(@RequestBody GroupMember groupMember){
        memberService.addMember(groupMember);
    }

    @DeleteMapping
    public void removeMember (@RequestBody GroupMember groupMember){
        memberService.removeMember(groupMember);
    }

    @GetMapping
    public GroupMember getGroupsByNick (@PathVariable(value = "nick") String nick){
        return memberService.getGroupsByNick(nick).orElseThrow(UserNotFoundException::new);
    }

    @DeleteMapping("/{nick}")
    public void removeMemberByNick(@PathVariable(value = "nick") String nick){
        memberService.removeMemberByNick(nick);
    }

    @DeleteMapping("/{id}")
    public void removeMembersFromGroup(@PathVariable(value = "id") Integer id){
        memberService.removeMembersFromGroup(id);
    }





}
