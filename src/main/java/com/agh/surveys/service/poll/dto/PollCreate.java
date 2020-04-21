package com.agh.surveys.service.poll.dto;

import com.agh.surveys.model.question.type.QuestionDetails;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PollCreate {


    private String pollName;
    private LocalDateTime polDeadline;
    private String authorId;
    private List<QuestionDetails> questionDetails;

    public PollCreate(String pollName, LocalDateTime polDeadline, String authorId, List<QuestionDetails> questionDetails) {
        this.pollName = pollName;
        this.polDeadline = polDeadline;
        this.authorId = authorId;
        this.questionDetails = questionDetails;
    }

    public PollCreate() {
    }

}
