package com.agh.surveys.model.message.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageCreateDto {

    private String context;
    private String authorNick;
    private LocalDateTime deadline;

}
