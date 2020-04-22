package com.agh.surveys.service.answer;

import com.agh.surveys.model.answer.Answer;
import com.agh.surveys.model.answer.type.AnswerDetails;

import java.util.List;

public interface IAnswerService {

    List<Answer> findAll(Long questionId);

    Answer addAnswer(Long questionID, String userID, AnswerDetails answerDetails);

    Answer getAnswer(Long AnswerId);

    void deleteAnswer(Long answerId);

}
