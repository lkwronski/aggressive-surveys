package com.agh.surveys.model.answer.type;

import com.agh.surveys.model.answer.dto.AnswerTimeDto;
import com.agh.surveys.model.answer.dto.AnswerTimeSlotDto;
import com.agh.surveys.model.question.type.QuestionType;
import com.agh.surveys.model.question.type.TimeSlot;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Entity
public class AnswerTime extends AnswerDetails{

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true )
    private List<AnswerTimeSlot> timeSlots;

    public AnswerTime(String answerText, List<AnswerTimeSlot> timeSlots){
        super(answerText);
        this.timeSlots = timeSlots;
    }

    public AnswerTime(){

    }

    public AnswerTime(AnswerTimeDto answerCreateDto, Map<AnswerTimeSlotDto,TimeSlot> slotsMap) {
        super(answerCreateDto.getAnswerText());
        this.timeSlots = answerCreateDto.getTimeSlots().stream()
                .map(timeSlotDto -> new AnswerTimeSlot(timeSlotDto,slotsMap.get(timeSlotDto)))
                .collect(Collectors.toList());
    }

    @Override
    public QuestionType getQuestionType() {
        return QuestionType.TIME;
    }

}
