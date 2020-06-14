package com.agh.surveys.model.question.dto;

import com.agh.surveys.model.question.type.QuestionType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class QuestionTimeDto extends  QuestionCreateDto {

    @Override
    public QuestionType getQuestionType() {
        return QuestionType.TIME;
    }
    private List<TimeSlotDto> timeSlots;

}
