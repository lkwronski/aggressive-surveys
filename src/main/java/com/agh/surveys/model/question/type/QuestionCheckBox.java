package com.agh.surveys.model.question.type;

import lombok.Data;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.List;

@Data
@Entity
public class QuestionCheckBox extends QuestionDetails {

    @ElementCollection
    private List<String> options;

    public QuestionCheckBox(String questionText, List<String> options) {
        super(questionText);
        this.options = options;
    }

    public QuestionCheckBox() {
    }
}
