package com.agh.surveys.validation;

import com.agh.surveys.exception.NotFoundException;
import com.agh.surveys.repository.ScheduledPollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScheduledPollValidator {

    @Autowired
    ScheduledPollRepository pollRepository;

    public void validateBeforeDelete(Integer pollId){
        if(!pollRepository.existsById(pollId)){
            throw new NotFoundException("No scheduled poll with shuch id");
        }
    }

}
