package com.agh.surveys.service.group;


import com.agh.surveys.exception.BusinessException;
import com.agh.surveys.exception.group.GroupNotFoundException;
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
import java.util.ArrayList;
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

    private static final int pollDeadlineMarginMinutes = 2;

    @Override
    public Poll addPolltoGroup(PollCreateDto pollCreateDto, Integer groupId) {

        Group group = getGroup(groupId);
        User author = userService.getUserByNick(pollCreateDto.getAuthorNick());

        validatePollDto(pollCreateDto);

        if (!userService.isUserInGroup(author, group)) {
            throw new BusinessException("Poll author must be member of a group.");
        }

        List<Question> questions = new LinkedList<>();
        Poll poll = new Poll(pollCreateDto.getPollName(), LocalDateTime.now(), pollCreateDto.getPolDeadline(), author, questions);
        pollService.savePoll(poll);
        questions.addAll(questionService.addAllQuestionDetails(poll, pollCreateDto.getQuestionDetails()));
        poll.setPollGroup(group);

        return poll;
    }

    private LocalDateTime createdPollDeadlineMargin(){
        return LocalDateTime.now().plusMinutes(pollDeadlineMarginMinutes);
    }

    private void validatePollDto(PollCreateDto pollCreateDto){
        if(pollCreateDto.getPolDeadline().isBefore(createdPollDeadlineMargin()) ){
            throw new BusinessException("Created poll can't have deadline in the past.");
        }
    }

    @Override
    public GroupRespDto addGroup(GroupCreateDto groupCreateDto) {
        User groupLeader = userRepository.getOne(groupCreateDto.getLeaderNick());
        List<User> members = new ArrayList<>(); // TODO dla pustej listy membersNick jest wyrzucany wyjątek

        for (String userName : groupCreateDto.getGroupMembersNicks()) {
            User user = userService.getUserByNick(userName);
            members.add(user);
        }
        //lider nie moze sie duplikować wsrod czlonkow
        members.remove(groupLeader);

        Group group = new Group(groupCreateDto.getGroupName(), groupLeader, members);
        groupRepository.save(group);

        return new GroupRespDto(group);
    }

    @Override
    public void removeGroup(Group group) {
        groupRepository.delete(group);
    }

    @Override
    public void removeGroup(Integer id) {
        if (groupRepository.existsById(id)){
            groupRepository.deleteById(id);
        }else{
            throw new GroupNotFoundException();
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
        User user = userRepository.getOne(userNick);

        if (group.getGroupMembers().contains(user)) {
            throw new BusinessException("This user is already in the group.");
        }

        group.getGroupMembers().add(user);

        groupRepository.save(group);
    }

    @Override
    public void removeGroupMember(Integer groupId, String userNick) {
        Group group = getGroup(groupId);
        User user = userService.getUserByNick(userNick);

        if (!group.getGroupMembers().contains(user)) {
            throw new BusinessException("Provided user is not in this group.");
        }

        group.getGroupMembers().remove(user);

        groupRepository.save(group);

    }
}
