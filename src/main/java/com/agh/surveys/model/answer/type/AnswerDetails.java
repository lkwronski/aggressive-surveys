package com.agh.surveys.model.answer.type;

import com.agh.surveys.model.question.type.QuestionType;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        property = "answerType", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = AnswerText.class, name = "TEXT"),
        @JsonSubTypes.Type(value = AnswerTime.class, name = "TIME"),
        @JsonSubTypes.Type(value = AnswerCheckBox.class, name = "CHECKBOX")
}
)abstract public class AnswerDetails {

    @Id
    @GeneratedValue
    private long id;
    private String answerText;

    public AnswerDetails(){
    }

    //I think that we should use only one enum from [Answer/Question]Type
    // as question and types are strictly connected, moreover
    // those enums have identical values/
    public abstract QuestionType getQuestionType();

    public AnswerDetails(String answerText){
        this.answerText = answerText;
    }
}

