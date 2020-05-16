package com.agh.surveys.repository;

import com.agh.surveys.model.question.ScheduledQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduledQuestionRepository extends JpaRepository<ScheduledQuestion,Integer> {
}
