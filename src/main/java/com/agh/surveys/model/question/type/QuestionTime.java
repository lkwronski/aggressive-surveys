package com.agh.surveys.model.question.type;

import com.agh.surveys.model.question.dto.QuestionTimeDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class QuestionTime extends QuestionDetails {


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true )
    private List<TimeSlot> timeSlots;

    public QuestionTime(String questionText, List<TimeSlot> timeSlots) {
        super(questionText);
        this.timeSlots = timeSlots;
    }

    public QuestionTime(QuestionTimeDto questionTimeDto){
        super(questionTimeDto.getQuestionText());
        this.timeSlots = questionTimeDto.getTimeSlots().stream()
                .map(TimeSlot::new)
                .collect(Collectors.toList());
    }

    public QuestionTime() {
    }

    @Override
    public QuestionType getQuestionType() {
        return QuestionType.TIME;
    }
}
