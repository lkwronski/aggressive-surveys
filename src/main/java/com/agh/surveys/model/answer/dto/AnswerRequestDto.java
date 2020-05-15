package com.agh.surveys.model.answer.dto;

import com.agh.surveys.model.answer.type.AnswerDetails;
import lombok.Data;

@Data
public class AnswerRequestDto {

    private Integer questionId;
    private AnswerDetails details;

}
