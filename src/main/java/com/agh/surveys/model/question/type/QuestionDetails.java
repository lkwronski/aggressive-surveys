package com.agh.surveys.model.question.type;


import com.agh.surveys.model.question.Question;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        property = "questionType", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = QuestionText.class, name = "TEXT"),
        @JsonSubTypes.Type(value = QuestionTime.class, name = "TIME"),
        @JsonSubTypes.Type(value = QuestionCheckBox.class, name = "CHECKBOX")
}
)abstract public class QuestionDetails {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Integer id;

    @Column(name = "text")
    private String questionText;

    public QuestionDetails(){

    }

    public QuestionDetails(String questionText) {
        this.questionText = questionText;
    }

}
