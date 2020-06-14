package com.agh.surveys.model.question.type;

import com.agh.surveys.model.answer.type.AnswerTimeSlot;
import com.agh.surveys.model.question.dto.TimeSlotDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class TimeSlot {

    @Id
    @GeneratedValue
    private Integer timeSlotId;

    @Column
    private LocalDate slotDay;

    @Column
    private LocalTime startHour;

    @Column
    private LocalTime endHour;

    @OneToMany(mappedBy = "timeSlot", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AnswerTimeSlot> answerSlots;

    public TimeSlot(TimeSlotDto timeSlotDto) {
        this.slotDay = timeSlotDto.getSlotDay();
        this.startHour = timeSlotDto.getStartHour();
        this.endHour = timeSlotDto.getEndHour();
        this.answerSlots = new LinkedList<>();
    }

}
