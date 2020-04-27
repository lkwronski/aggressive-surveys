package com.agh.surveys.model.question.dto;

import com.agh.surveys.model.poll.Poll;
import com.agh.surveys.model.question.Question;
import com.agh.surveys.model.question.type.QuestionDetails;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@NoArgsConstructor
public class QuestionResponse {

    private Integer questionId;
    private QuestionDetails questionDetails;
    private Integer pollId;

    public QuestionResponse(Question question) {
        this.questionId = question.getQuestionId();
        this.questionDetails = question.getQuestionDetails();
        this.pollId = question.getQuestionPoll().getPollId();
    }

}
