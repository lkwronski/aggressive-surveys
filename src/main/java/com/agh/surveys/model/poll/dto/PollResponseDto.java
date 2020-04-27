package com.agh.surveys.model.poll.dto;

import com.agh.surveys.model.group.Group;
import com.agh.surveys.model.poll.Poll;
import com.agh.surveys.model.question.dto.QuestionResponse;
import com.agh.surveys.model.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class PollResponseDto {

    private Integer pollId;
    private String pollName;
    private LocalDateTime pollCreationTime;
    private LocalDateTime polDeadline;
    private Group pollGroup;
    private User author;
    private List<QuestionResponse> questions;

    public PollResponseDto(Poll poll) {
        this.pollId = poll.getPollId();
        this.pollName = poll.getPollName();
        this.pollCreationTime = poll.getPollCreationTime();
        this.polDeadline = poll.getPolDeadline();
        this.pollGroup = poll.getPollGroup();
        this.author = poll.getAuthor();
        this.questions = poll.getQuestions().stream().map(QuestionResponse::new).collect(Collectors.toList());
    }
}
