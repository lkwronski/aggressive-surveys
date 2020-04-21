package com.agh.surveys.model.answer.type;

import lombok.Data;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.List;

@Data
@Entity
public class AnswerCheckBox extends AnswerDetails{

    @ElementCollection
    private List<String> selectedOptions;

    public AnswerCheckBox(String answerText, List<String> selectedOptions){
        super(answerText);
        this.selectedOptions = selectedOptions;
    }

    public AnswerCheckBox(){
    }
}
