package com.agh.surveys.service.group;


import com.agh.surveys.component.group.GroupComponent;
import com.agh.surveys.exception.group.GroupNotFoundException;
import com.agh.surveys.exception.user.UserNotFoundException;
import com.agh.surveys.model.group.Group;
import com.agh.surveys.model.group.dto.GroupCreateDto;
import com.agh.surveys.model.group.dto.GroupRespDto;
import com.agh.surveys.model.poll.Poll;
import com.agh.surveys.model.poll.dto.PollCreateDto;
import com.agh.surveys.model.poll.dto.PollResponseDto;
import com.agh.surveys.model.question.Question;
import com.agh.surveys.model.user.User;
import com.agh.surveys.repository.GroupRepository;
import com.agh.surveys.repository.PollRepository;
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
import java.util.stream.Collectors;

// I changed searching by name to searching by Id as we didn't assume that group's name is unique (LK)
@Service
public class GroupService implements IGroupService {

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    GroupComponent groupComponent;

    @Autowired
    UserService userService;

    @Autowired
    PollService pollService;

    @Autowired
    QuestionService questionService;

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

    @Override
    public Poll addPolltoGroup(PollCreateDto pollCreateDto, Integer groupId) {

        Group group = getGroup(groupId);
        User author = userService.getUserByNick(pollCreateDto.getAuthorNick());

        List<Question> questions = new LinkedList<>();
        Poll poll = new Poll(pollCreateDto.getPollName(), LocalDateTime.now(), pollCreateDto.getPolDeadline(), author, questions);
        pollService.savePoll(poll);
        questions.addAll(questionService.addAllQuestionDetails(poll, pollCreateDto.getQuestionDetails()));
        poll.setPollGroup(group);
        groupRepository.save(group);
        return poll;
    }

    @Override
    public Integer addGroup(GroupCreateDto groupCreateDto) {
        User groupLeader = userRepository.getOne(groupCreateDto.getLeaderNick());
        List<User> members; // TODO dla pustej listy membersNick jest wyrzucany wyjątek
        if ( groupCreateDto.getGroupMembersNicks().isEmpty()) {
            members = Collections.emptyList();
        }
        else {
           members = userRepository.findByUserNickIn(groupCreateDto.getGroupMembersNicks())
                    .orElseThrow(UserNotFoundException::new);
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

        group.getGroupMembers().remove(user);

        saveGroupAndUser(group, user);

    }
}
