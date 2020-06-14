package com.agh.surveys.model.answer.type;

import com.agh.surveys.model.answer.dto.AnswerCheckBoxDto;
import com.agh.surveys.model.question.type.QuestionType;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.List;

@Data
@Entity
public class AnswerCheckBox extends AnswerDetails {

    @ElementCollection
    @Column(name = "options")
    private List<String> selectedOptions;

    public AnswerCheckBox(String answerText, List<String> selectedOptions) {
        super(answerText);
        this.selectedOptions = selectedOptions;
    }

    public AnswerCheckBox() {
    }

    public AnswerCheckBox(AnswerCheckBoxDto answerCreateDto) {
        super(answerCreateDto.getAnswerText());
        this.selectedOptions = answerCreateDto.getOptions();
    }

    @Override
    public QuestionType getQuestionType() {
        return QuestionType.CHECKBOX;
    }
}
