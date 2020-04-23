package com.agh.surveys.service.question;

import com.agh.surveys.model.question.Question;
import com.agh.surveys.model.question.type.QuestionDetails;

import java.util.List;

public interface IQuestionService {

    List<Question> getByPollId(Integer poolId);

    Question addQuestion(Integer poolId, QuestionDetails questionDetails);

    Question getQuestion(Integer questionId);

    void deleteQuestion(Integer questionId);

    List<Question> addAllQuestionDetails(List<QuestionDetails> questionDetails);
}
