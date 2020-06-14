package com.agh.surveys.repository;

import com.agh.surveys.model.group.Group;
import com.agh.surveys.model.poll.ScheduledPoll;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduledPollRepository extends JpaRepository<ScheduledPoll, Integer> {

    List<ScheduledPoll> findAllByPollGroup(Group pollGroup);
}
