package com.agh.surveys.service.group;


import com.agh.surveys.exception.group.GroupNotFoundException;
import com.agh.surveys.exception.user.UserNotFoundException;
import com.agh.surveys.model.group.Group;
import com.agh.surveys.model.group.dto.GroupDto;
import com.agh.surveys.model.poll.Poll;
import com.agh.surveys.model.poll.dto.PollCreateDto;
import com.agh.surveys.model.question.Question;
import com.agh.surveys.model.user.User;
import com.agh.surveys.repository.GroupRepository;
import com.agh.surveys.repository.UserRepository;
import com.agh.surveys.service.question.QuestionService;
import com.agh.surveys.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

// I changed searching by name to searching by Id as we didn't assume that group's name is unique (LK)
@Service
public class GroupService implements IGroupService {

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    QuestionService questionService;

    @Override
    public Poll addPolltoGroup(PollCreateDto pollCreateDto, Integer groupId) {
        Group group = groupRepository.findById(groupId).orElseThrow(GroupNotFoundException::new);
        User author = userService.getUserByNick(pollCreateDto.getAuthorId());
        List<Question> questions = questionService.addAllQuestionDetails(pollCreateDto.getQuestionDetails());
        Poll poll = new Poll(pollCreateDto.getPollName(), LocalDateTime.now(), pollCreateDto.getPolDeadline(), author, questions);
        return poll;
    }

    @Override
    public Integer addGroup(GroupDto groupDto) {
        User groupLeader = userRepository.getOne(groupDto.getLeaderNick());
        List<User> members = userRepository.findByUserNickIn(groupDto.getGroupMembersNicks())
                .orElseThrow(UserNotFoundException::new);

        Group group = new Group(groupDto.getGroupName(), groupLeader, members);

        return groupRepository.save(group).getId();

    }

    @Override
    public void removeGroup(Group group) {
        groupRepository.delete(group);
    }

    @Override
    public void removeGroup(Integer id) {
        groupRepository.deleteById(id);
    }

    @Override
    public GroupDto getGroupDto(Integer id) {
        Group group=  groupRepository.findById(id)
                .orElseThrow(GroupNotFoundException::new);
        return new GroupDto(group);
    }

    @Override
    public Group getGroup(Integer id){
        return groupRepository.getOne(id);
    }

    //@TODO Maybe there is some way to update only group and the user will be updated as well? (LK)
    @Override
    public void addGroupMember(Integer groupId, String userNick) {
        Group group = groupRepository.getOne(groupId);
        User user = userRepository.getOne(userNick);

        group.getGroupMembers().add(user);
        user.getUserGroups().add(group);

        saveGroupAndUser(group, user);
    }

    private void saveGroupAndUser(Group group, User user) {
        groupRepository.save(group);
        userRepository.save(user);
    }

    @Override
    public void removeGroupMember(Integer groupId, String userNick) {
        Group group = groupRepository.getOne(groupId);
        User user = userRepository.getOne(userNick);

        group.getGroupMembers().remove(user);
        user.getUserGroups().remove(group);

        saveGroupAndUser(group, user);

    }
}
