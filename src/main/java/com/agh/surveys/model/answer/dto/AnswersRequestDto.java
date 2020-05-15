package com.agh.surveys.model.answer.dto;

import lombok.Data;

import java.util.List;

@Data
public class AnswersRequestDto {

    List<AnswerRequestDto> answers;
    private String answerAuthor;

}
