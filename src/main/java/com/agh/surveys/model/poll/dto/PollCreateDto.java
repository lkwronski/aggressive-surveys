package com.agh.surveys.model.poll.dto;

import com.agh.surveys.model.question.type.QuestionDetails;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PollCreateDto {


    private String pollName;
    private LocalDateTime polDeadline;
    private String authorNick;
    private List<QuestionDetails> questionDetails;

    public PollCreateDto(String pollName, LocalDateTime polDeadline, String authorNick, List<QuestionDetails> questionDetails) {
        this.pollName = pollName;
        this.polDeadline = polDeadline;
        this.authorNick = authorNick;
        this.questionDetails = questionDetails;
    }

    public PollCreateDto() {
    }

}
