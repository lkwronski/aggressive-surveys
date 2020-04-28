package com.agh.surveys.service.group;


import com.agh.surveys.exception.group.GroupNotFoundException;
import com.agh.surveys.exception.group.UserAlreadyInTheGroupException;
import com.agh.surveys.exception.group.UserNotInTheGroupException;
import com.agh.surveys.exception.user.UserNotFoundException;
import com.agh.surveys.model.group.Group;
import com.agh.surveys.model.group.dto.GroupCreateDto;
import com.agh.surveys.model.group.dto.GroupRespDto;
import com.agh.surveys.model.poll.Poll;
import com.agh.surveys.model.poll.dto.PollCreateDto;
import com.agh.surveys.model.question.Question;
import com.agh.surveys.model.user.User;
import com.agh.surveys.repository.GroupRepository;
import com.agh.surveys.repository.QuestionRepository;
import com.agh.surveys.repository.UserRepository;
import com.agh.surveys.service.poll.PollService;
import com.agh.surveys.service.question.QuestionService;
import com.agh.surveys.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

// I changed searching by name to searching by Id as we didn't assume that group's name is unique (LK)
@Service
public class GroupService implements IGroupService {

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    UserService userService;

    @Autowired
    PollService pollService;

    @Autowired
    QuestionService questionService;

    @Override
    public Poll addPolltoGroup(PollCreateDto pollCreateDto, Integer groupId) {
        Group group = groupRepository.findById(groupId).orElseThrow(GroupNotFoundException::new);
        User author = userService.getUserByNick(pollCreateDto.getAuthorId());
        List<Question> questions = new LinkedList<>();
        Poll poll = new Poll(pollCreateDto.getPollName(), LocalDateTime.now(), pollCreateDto.getPolDeadline(), author, questions);
        pollService.savePoll(poll);
        questions.addAll(questionService.addAllQuestionDetails(poll, pollCreateDto.getQuestionDetails()));
        pollService.savePoll(poll);
        group.getGroupPolls().add(poll);
        groupRepository.save(group);
        return poll;
    }

    @Override
    public Integer addGroup(GroupCreateDto groupCreateDto) {
        User groupLeader = userRepository.getOne(groupCreateDto.getLeaderNick());
        List<User> members = Collections.emptyList(); // TODO dla pustej listy membersNick jest wyrzucany wyjątek
        if ( !groupCreateDto.getGroupMembersNicks().isEmpty()) {
            for (String userName : groupCreateDto.getGroupMembersNicks()) {
                User user = userService.getUserByNick(userName);
                members.add(user);
            }
        }
        Group group = new Group(groupCreateDto.getGroupName(), groupLeader, members);

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
    public GroupRespDto getGroupDto(Integer id) {
        Group group=  groupRepository.findById(id)
                .orElseThrow(GroupNotFoundException::new);
        return new GroupRespDto(group);
    }

    @Override
    public Group getGroup(Integer id){
        return groupRepository.findById(id).orElseThrow(GroupNotFoundException::new);
    }

    //@TODO Maybe there is some way to update only group and the user will be updated as well? (LK)
    @Override
    public void addGroupMember(Integer groupId, String userNick) {
        Group group = groupRepository.getOne(groupId);
        User user = userRepository.getOne(userNick);

        if(group.getGroupMembers().contains(user)){
            throw new UserAlreadyInTheGroupException();
        }

        group.getGroupMembers().add(user);

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

        if(!group.getGroupMembers.contains(user)){
            throw new UserNotInTheGroupException();
        }

        group.getGroupMembers().remove(user);

        saveGroupAndUser(group, user);

    }
}
