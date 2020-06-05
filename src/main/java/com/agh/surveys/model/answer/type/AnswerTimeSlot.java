package com.agh.surveys.model.answer.type;

import com.agh.surveys.model.answer.dto.AnswerTimeSlotDto;
import com.agh.surveys.model.question.type.TimeSlot;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@Entity
public class AnswerTimeSlot {

    @Id
    @GeneratedValue
    private Integer answerTimeSlotId;

    @Column
    private LocalDate slotDay;

    @Column
    private LocalTime startHour;

    @Column
    private LocalTime endHour;

    @ManyToOne
    @JoinColumn(name = "timeSlotId")
    private TimeSlot timeSlot;


    public AnswerTimeSlot(AnswerTimeSlotDto timeSlotDto, TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
        this.slotDay = timeSlotDto.getSlotDay();
        this.startHour = timeSlotDto.getStartHour();
        this.endHour = timeSlotDto.getEndHour();
    }
}
