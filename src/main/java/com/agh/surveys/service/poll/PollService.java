package com.agh.surveys.service.poll;

import com.agh.surveys.exception.PollNotFoundException;
import com.agh.surveys.model.User;
import com.agh.surveys.model.poll.question.Poll;
import com.agh.surveys.model.poll.question.Question;
import com.agh.surveys.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PollService implements IPollService{

    @Autowired
    PollRepository pollRepository;

    @Override
    public List<Poll> findAll() {
        return pollRepository.findAll();
    }

    @Override
    public Poll findPoll(Long id) {
        return pollRepository.findById(id)
                .orElseThrow(() -> new PollNotFoundException(id));
    }


    @Override
    public Poll addPoll(String pollName, LocalDateTime pollDeadline, User author, List<Question> questions) {
        Poll poll = new Poll(pollName, LocalDateTime.now(), pollDeadline, author, questions);
        return pollRepository.save(poll);
    }

    @Override
    public void deletePoll(Long id) {
        pollRepository.deleteById(id);
    }
}
