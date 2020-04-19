package com.agh.surveys.service.question;

import com.agh.surveys.model.poll.question.Question;
import com.agh.surveys.model.poll.question.type.QuestionDetails;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface IQuestionService {

    List<Question> findAll();

    Question addQuestion(QuestionDetails questionDetails);

    Question findQuestion(Long id);

    void deleteQuestion(Long id);
}
