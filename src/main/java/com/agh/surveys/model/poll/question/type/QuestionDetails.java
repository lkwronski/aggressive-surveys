package com.agh.surveys.model.poll.question.type;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value = QuestionText.class, name = "TEXT"),
        @JsonSubTypes.Type(value = QuestionTime.class, name = "TIME"),
        @JsonSubTypes.Type(value = QuestionCheckBox.class, name = "CHECKBOX")
}
)abstract public class QuestionDetails {

    @Id
    @GeneratedValue
    private long id;
    private String questionText;

    public QuestionDetails(){

    }

    public QuestionDetails(String questionText) {
        this.questionText = questionText;
    }

}
