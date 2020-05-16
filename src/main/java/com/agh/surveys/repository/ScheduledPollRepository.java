package com.agh.surveys.repository;

import com.agh.surveys.model.poll.ScheduledPoll;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduledPollRepository extends JpaRepository<ScheduledPoll, Integer> {
}
