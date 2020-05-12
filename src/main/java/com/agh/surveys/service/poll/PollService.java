package com.agh.surveys.service.poll;

import com.agh.surveys.component.group.GroupComponent;
import com.agh.surveys.exception.poll.PollNotFoundException;
import com.agh.surveys.model.poll.Poll;
import com.agh.surveys.model.user.User;
import com.agh.surveys.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PollService implements IPollService{

    @Autowired
    PollRepository pollRepository;

    @Autowired
    GroupComponent groupComponent;

    @Override
    public List<Poll> findAll() {
        return pollRepository.findAll();
    }

    @Override
    public Poll getPoll(Integer id) {
        return pollRepository.findById(id)
                .orElseThrow(PollNotFoundException::new);
    }

    @Override
    public void deletePoll(Integer id) {
        pollRepository.deleteById(id);
    }

    @Override
    public Poll savePoll(Poll poll) {
       return pollRepository.save(poll);
    }

    @Override
    public List<User> getRespondedUser(Integer pollId) {
        Poll poll = getPoll(pollId);
        List<User> groupUsers = poll.getPollGroup().getGroupMembers();
        List<User> respondedUsers = groupUsers.stream().filter( user -> groupComponent.userResponseToPoll(user, poll)).collect(Collectors.toList());
        return respondedUsers;
    }

    @Override
    public List<User> getNotRespondedUser(Integer pollId) {
        Poll poll = getPoll(pollId);
        List<User> groupUsers = poll.getPollGroup().getGroupMembers();
        List<User> respondedUsers = groupUsers.stream().filter( user -> groupComponent.userNotResponseToPoll(user, poll)).collect(Collectors.toList());
        return respondedUsers;
    }
}
