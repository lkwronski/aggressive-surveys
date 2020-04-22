package com.agh.surveys.model.question.type;

import lombok.Data;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.List;

@Data
@Entity
public class QuestionTime extends QuestionDetails {

    @ElementCollection
    private List<String> fillPrompts;

    public QuestionTime(String questionText, List<String> fillPrompts) {
        super(questionText);
        this.fillPrompts = fillPrompts;
    }

    public QuestionTime() {
    }
}
