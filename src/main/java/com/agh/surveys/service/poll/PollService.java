package com.agh.surveys.service.poll;

import com.agh.surveys.exception.poll.PollNotFoundException;
import com.agh.surveys.model.poll.Poll;
import com.agh.surveys.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
