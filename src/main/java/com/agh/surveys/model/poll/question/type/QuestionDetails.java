package com.agh.surveys.model.poll.question.type;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
abstract public class QuestionDetails {

    @Id
    @GeneratedValue
    private long id;
    private String questionText;
    private QuestionType questionType;

    public QuestionDetails(){

    }

    public QuestionDetails(String questionText, QuestionType questionType) {
        this.questionText = questionText;
        this.questionType = questionType;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }
}
