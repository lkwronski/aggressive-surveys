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

    @Override
    public QuestionType getQuestionType() {
        return QuestionType.TEXT;
    }

    @Override
    public String toString(){
        return "id: " +
                super.getId() +
                "\nquestionText: " +
                super.getQuestionText();
    }
}
