package com.agh.surveys.model.answer.dto;

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
        property = "answerType", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = AnswerTextDto.class, name = "TEXT"),
        @JsonSubTypes.Type(value = AnswerTimeDto.class, name = "TIME"),
        @JsonSubTypes.Type(value = AnswerCheckBoxDto.class, name = "CHECKBOX")})
abstract public class AnswerCreateDto {

    private String answerText;

    public AnswerCreateDto(String text) {
        this.answerText=text;
    }

    public abstract QuestionType getQuestionType();
}