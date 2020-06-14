package com.agh.surveys.model.question.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
public class TimeSlotDto {

    private LocalDate slotDay;
    private LocalTime startHour;
    private LocalTime endHour;


}
