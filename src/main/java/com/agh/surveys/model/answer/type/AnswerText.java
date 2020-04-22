package com.agh.surveys.model.answer.type;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class AnswerText extends AnswerDetails{

    public AnswerText(String answerText){ super(answerText);}

    public AnswerText()
    {}
}
