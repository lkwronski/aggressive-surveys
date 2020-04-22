package com.agh.surveys.model.answer.type;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value = AnswerText.class, name = "TEXT"),
        @JsonSubTypes.Type(value = AnswerTime.class, name = "TIME"),
        @JsonSubTypes.Type(value = AnswerCheckBox.class, name = "CHECKBOX")
}
)abstract public class AnswerDetails {

    @Id
    @GeneratedValue
    private long id;
    private String answerText; //in order to comment out answer

    public AnswerDetails(){
    }

    public AnswerDetails(String answerText){
        this.answerText = answerText;
    }
}

