package com.agh.surveys.service.question;

import com.agh.surveys.model.question.Question;
import com.agh.surveys.model.question.type.QuestionDetails;

import java.util.List;

public interface IQuestionService {

    List<Question> findAll(Long poolId);

    Question addQuestion(Long poolId, QuestionDetails questionDetails);

    Question getQuestion(Long questionId);

    void deleteQuestion(Long questionId);

    List<Question> addAllQuestionDetails(List<QuestionDetails> questionDetails);
}
