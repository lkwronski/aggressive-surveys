package com.agh.surveys.model.poll.dto;

import com.agh.surveys.model.question.type.QuestionDetails;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ScheduledPollCreateDto {

    String pollName;
    private LocalDateTime pollCreationTime;
    private String authorNick;
    private List<QuestionDetails> questionDetails;
    private String scheduledInterval;
    private String deadlineInterval;
}
