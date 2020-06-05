package com.agh.surveys.model.answer.dto;

import com.agh.surveys.model.question.type.QuestionType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class AnswerTextDto extends AnswerCreateDto {

    @Override
    public QuestionType getQuestionType() {
        return QuestionType.TEXT;
    }

    public AnswerTextDto(String text){
        super(text);
    }
}
