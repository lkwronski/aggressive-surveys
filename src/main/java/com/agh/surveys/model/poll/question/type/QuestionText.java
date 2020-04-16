package com.agh.surveys.model.poll.question.type;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class QuestionText extends QuestionDetails {

    public QuestionText(String questionText) {
        super(questionText, QuestionType.TEXT);
    }

    public QuestionText() {
    }
}
