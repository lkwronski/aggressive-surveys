package com.agh.surveys.model.poll.dto;

import com.agh.surveys.model.poll.ScheduledPoll;
import com.agh.surveys.model.question.dto.QuestionResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class ScheduledPollResponseDto {

    private Integer pollId;
    private String pollName;
    private LocalDateTime pollScheduleTime;
    private String scheduleInterval;
    private String deadlineInterval;
    private Integer pollGroupId;
    private String authorNick;
    private List<QuestionResponse> questions;

    public ScheduledPollResponseDto(ScheduledPoll poll, String scheduleInterval, String deadlineInterval) {
        this.pollId = poll.getPollId();
        this.pollName = poll.getPollName();
        this.pollScheduleTime = poll.getPollScheduleTime();
        this.scheduleInterval = scheduleInterval;
        this.deadlineInterval = deadlineInterval;
        this.pollGroupId = poll.getPollGroup().getId();
        this.authorNick = poll.getAuthor().getUserNick();
        this.questions = poll.getQuestions().stream().map(QuestionResponse::new).collect(Collectors.toList());
    }
}
