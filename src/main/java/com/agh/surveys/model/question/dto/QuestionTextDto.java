package com.agh.surveys.model.question.dto;

import com.agh.surveys.model.question.type.QuestionType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class QuestionTextDto extends QuestionCreateDto {
    @Override
    public QuestionType getQuestionType() {
        return QuestionType.TEXT;
    }

    public QuestionTextDto(String text){
        super(text);
    }
}
