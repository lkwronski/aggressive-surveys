package com.agh.surveys.service.answer;

import com.agh.surveys.model.answer.Answer;
import com.agh.surveys.model.answer.dto.AnswerResponse;
import com.agh.surveys.model.answer.dto.AnswersRequestDto;
import com.agh.surveys.model.answer.type.AnswerDetails;
import com.agh.surveys.model.question.Question;

import java.util.List;

public interface IAnswerService {

    List<Answer> getQuestionAnswers(Integer questionId);

    Answer addAnswer(Integer questionID, String userID, AnswerDetails answerDetails);

    Answer getAnswer(Integer AnswerId);

    void deleteAnswer(Integer answerId);

    List<AnswerResponse> answerQuestion(Integer pollId, AnswersRequestDto answersRequestDto);

    List<Answer> getAnswerForUserInQuestion(Integer questionID, String userNick);

}
