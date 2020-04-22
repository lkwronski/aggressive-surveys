package com.agh.surveys.model.question.type;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class QuestionText extends QuestionDetails {

    public QuestionText(String questionText) {
        super(questionText);
    }

    public QuestionText() {
    }
}
