package com.agh.surveys.service.group;


<<<<<<< HEAD
import com.agh.surveys.exception.BadRequestException;
import com.agh.surveys.exception.NotFoundException;
=======
import com.agh.surveys.component.group.GroupComponent;
>>>>>>> master
import com.agh.surveys.exception.group.GroupNotFoundException;
import com.agh.surveys.model.group.Group;
import com.agh.surveys.model.group.dto.GroupCreateDto;
import com.agh.surveys.model.group.dto.GroupRespDto;
import com.agh.surveys.model.poll.Poll;
import com.agh.surveys.model.poll.dto.PollCreateDto;
import com.agh.surveys.model.question.Question;
import com.agh.surveys.model.user.User;
import com.agh.surveys.repository.GroupRepository;
import com.agh.surveys.service.poll.PollService;
import com.agh.surveys.service.question.QuestionService;
import com.agh.surveys.service.user.UserService;
import com.agh.surveys.validation.GroupValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupService implements IGroupService {

    @Autowired
    GroupRepository groupRepository;

    @Autowired
<<<<<<< HEAD
=======
    UserRepository userRepository;

    @Autowired
    GroupComponent groupComponent;

    @Autowired
>>>>>>> master
    UserService userService;

    @Autowired
    PollService pollService;

    @Autowired
    QuestionService questionService;

<<<<<<< HEAD
    @Autowired
    GroupValidator groupValidator;
=======
    @Override
    public List<Poll> getFilledPolls(Integer groupId, String userNick) {
        Group group = getGroup(groupId);
        User user = userService.getUserByNick(userNick);
        List<Poll> groupPolls = group.getGroupPolls();
        return groupPolls.stream().filter(poll -> groupComponent.userResponseToPoll(user,poll)).collect(Collectors.toList());
    }

    @Override
    public List<Poll> getUnfilledPolls(Integer groupId, String userNick) {
        Group group = getGroup(groupId);
        User user = userService.getUserByNick(userNick);
        List<Poll> groupPolls = group.getGroupPolls();
        return groupPolls.stream().filter(poll -> groupComponent.userNotResponseToPoll(user,poll)).collect(Collectors.toList());
    }
>>>>>>> master

    @Override
    public Poll addPolltoGroup(PollCreateDto pollCreateDto, Integer groupId) {

        Group group = getGroup(groupId);
        User author = userService.getUserByNick(pollCreateDto.getAuthorNick());
<<<<<<< HEAD
        groupValidator.validateCreatePollDto(pollCreateDto, author, group);
=======
>>>>>>> master

        List<Question> questions = new LinkedList<>();
        Poll poll = new Poll(pollCreateDto.getPollName(), LocalDateTime.now(), pollCreateDto.getPolDeadline(), author, questions);
        pollService.savePoll(poll);
        questions.addAll(questionService.addAllQuestionDetails(poll, pollCreateDto.getQuestionDetails()));
        poll.setPollGroup(group);
        groupRepository.save(group);
<<<<<<< HEAD

=======
>>>>>>> master
        return poll;
    }

    @Override
    public GroupRespDto addGroup(GroupCreateDto groupCreateDto) {
        groupValidator.validateCreateGroupDto(groupCreateDto);

        User groupLeader;
        try {
            groupLeader = userService.getUserByNick(groupCreateDto.getLeaderNick());
        } catch (NotFoundException ex) {
            throw new NotFoundException("Cannot find person added as group leader");
        }

        List<User> members = new ArrayList<>();

        groupCreateDto.getGroupMembersNicks()
                .stream()
                .distinct() //usuniecie duplikatów
                .filter(nick -> !nick.equals(groupLeader.getUserNick())) //lider nie może się pojawic wsrod czlonkow
                .forEach(nick -> members.add(userService.getUserByNick(nick)));

        Group group = new Group(groupCreateDto.getGroupName(), groupLeader, members);
        groupRepository.save(group);

        return new GroupRespDto(group);
    }

    @Override
    public void removeGroup(Integer id) {
        if (groupRepository.existsById(id)) {
            groupRepository.deleteById(id);
        } else {
            throw new NotFoundException("Cannot find such group");
        }
    }

    @Override
    public GroupRespDto getGroupDto(Integer id) {
        Group group = groupRepository.findById(id)
                .orElseThrow(GroupNotFoundException::new);
        return new GroupRespDto(group);
    }

    @Override
    public Group getGroup(Integer id) {
        return groupRepository.findById(id).orElseThrow(GroupNotFoundException::new);
    }

    @Override
    public void addGroupMember(Integer groupId, String userNick) {
        Group group = groupRepository.getOne(groupId);
        User user = userService.getUserByNick(userNick);
        groupValidator.validateBeforeAddingUser(group,user);

        group.getGroupMembers().add(user);
        groupRepository.save(group);
    }

    @Override
    public void removeGroupMember(Integer groupId, String userNick) {
        Group group = getGroup(groupId);
        User user = userService.getUserByNick(userNick);

        groupValidator.validateBeforeRemovingUser(group,user);

        group.getGroupMembers().remove(user);

        groupRepository.save(group);
    }

    @Override
    public void saveGroup(Group group){
        groupRepository.save(group);
    }
}
