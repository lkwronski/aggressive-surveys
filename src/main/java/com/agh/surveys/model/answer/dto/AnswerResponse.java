package com.agh.surveys.model.answer.dto;

import com.agh.surveys.model.answer.Answer;
import com.agh.surveys.model.answer.type.AnswerDetails;
import com.agh.surveys.model.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class AnswerResponse {

    private Integer id;
    private Integer questionId;
    private String answerAuthorNick;
    private LocalDateTime answerDate;
    private AnswerDetails answerDetails;

    public AnswerResponse(Answer answer) {
        this.id = answer.getId();
        this.questionId = answer.getQuestion().getQuestionId();
        this.answerAuthorNick = answer.getAnswerAuthor().getUserNick();
        this.answerDate = answer.getAnswerDate();
        this.answerDetails = answer.getAnswerDetails();
    }
}
