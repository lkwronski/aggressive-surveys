package com.agh.surveys.service.poll;

import com.agh.surveys.model.poll.ScheduledPoll;
import com.agh.surveys.repository.ScheduledPollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduledPollService implements  IScheduledPollService {


    @Autowired
    ScheduledPollRepository pollRepository;

    @Override
    public void savePoll(ScheduledPoll poll) {
        pollRepository.save(poll);
    }
}
