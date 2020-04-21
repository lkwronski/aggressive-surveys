package com.agh.surveys.service.answer;

import com.agh.surveys.model.answer.Answer;
import com.agh.surveys.model.answer.type.AnswerDetails;

import java.util.List;

public interface IAnswerService {

    List<Answer> findAll(Long questionId); //returns all answers for a question

    Answer addAnswer(Long questionID, String userID, AnswerDetails answerDetails); //adds answer for a question

    Answer getAnswer(Long AnswerId); //get answer for a question

    void deleteAnswer(Long answerId); //delete answer <we need questionId? ~probably not>

    //TODO dodać opcję put

//    List<Answer> addAllAnswerDetails(List<AnswerDetails> answerDetails);
}
