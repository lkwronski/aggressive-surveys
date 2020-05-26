package com.agh.surveys.model.answer.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
public class AnswerTimeSlotDto {

    private LocalDate slotDay;
    private LocalTime startHour;
    private LocalTime endHour;
    private int slotTimeId;

}
