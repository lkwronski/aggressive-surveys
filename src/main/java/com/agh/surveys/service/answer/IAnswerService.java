package com.agh.surveys.service.answer;

import com.agh.surveys.model.answer.Answer;
import com.agh.surveys.model.answer.type.AnswerDetails;

import java.util.List;

public interface IAnswerService {

    List<Answer> findAll(Integer questionId);

    Answer addAnswer(Integer questionID, String userID, AnswerDetails answerDetails);

    Answer getAnswer(Integer AnswerId);

    void deleteAnswer(Integer answerId);

}
