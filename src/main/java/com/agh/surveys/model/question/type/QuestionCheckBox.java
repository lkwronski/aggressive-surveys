package com.agh.surveys.model.question.type;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.List;

@Data
@Entity
public class QuestionCheckBox extends QuestionDetails {

    @ElementCollection
    @Column(name = "options")
    private List<String> options;

    public QuestionCheckBox(String questionText, List<String> options) {
        super(questionText);
        this.options = options;
    }

    public QuestionCheckBox() {
    }

    @Override
    public QuestionType getQuestionType() {
        return QuestionType.CHECKBOX;
    }
}
