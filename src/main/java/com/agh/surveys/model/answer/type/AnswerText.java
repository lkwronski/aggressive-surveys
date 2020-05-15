package com.agh.surveys.model.answer.type;

import com.agh.surveys.model.question.type.QuestionType;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class AnswerText extends AnswerDetails{

    public AnswerText(String answerText){ super(answerText);}

    public AnswerText()
    {}

    @Override
    public QuestionType getQuestionType() {
        return QuestionType.TEXT;
    }
}
