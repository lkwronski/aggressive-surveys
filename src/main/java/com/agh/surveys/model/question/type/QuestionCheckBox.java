package com.agh.surveys.model.question.type;

import com.agh.surveys.model.question.dto.QuestionCheckBoxDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
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

    public QuestionCheckBox(QuestionCheckBoxDto questionCreateDto) {
        super(questionCreateDto.getQuestionText());
        this.options = questionCreateDto.getOptions();
    }

    @Override
    public QuestionType getQuestionType() {
        return QuestionType.CHECKBOX;
    }
}
