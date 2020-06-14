package com.agh.surveys.model.question.dto;

import com.agh.surveys.model.question.type.QuestionType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        property = "questionType", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = QuestionTextDto.class, name = "TEXT"),
        @JsonSubTypes.Type(value = QuestionTimeDto.class, name = "TIME"),
        @JsonSubTypes.Type(value = QuestionCheckBoxDto.class, name = "CHECKBOX")})
abstract public class QuestionCreateDto {

    private String questionText;

    public QuestionCreateDto(String text) {
        this.questionText=text;
    }

    public abstract QuestionType getQuestionType();
}
