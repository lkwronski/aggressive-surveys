package com.agh.surveys.model.poll.question.repository;

import com.agh.surveys.model.poll.question.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends  JpaRepository<Question, Long>  {

}
