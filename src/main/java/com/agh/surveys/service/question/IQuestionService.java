package com.agh.surveys.service.question;

import com.agh.surveys.model.poll.question.Question;
import com.agh.surveys.model.poll.question.type.QuestionDetails;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface IQuestionService {

    List<Question> findAll(Long poolId);

    Question addQuestion(Long poolId, QuestionDetails questionDetails);

    Question getQuestion(Long poolId, Long questionId);

    void deleteQuestion(Long poolId, Long questionId);

    List<Question> addAllQuestionDetails(List<QuestionDetails> questionDetails);
}
