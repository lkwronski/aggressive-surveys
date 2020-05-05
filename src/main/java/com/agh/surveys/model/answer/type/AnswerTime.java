package com.agh.surveys.model.answer.type;

import com.agh.surveys.model.question.type.QuestionType;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.List;

@Data
@Entity
public class AnswerTime extends AnswerDetails{

    @ElementCollection
    @Column(name = "prompts")
    private List<String> filledPrompts;

    public AnswerTime(String answerText, List<String> filledPrompts){
        super(answerText);
        this.filledPrompts = filledPrompts;
    }

    public AnswerTime(){

    }

    @Override
    public QuestionType getQuestionType() {
        return QuestionType.TIME;
    }

}
