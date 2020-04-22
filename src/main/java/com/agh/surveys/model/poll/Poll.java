package com.agh.surveys.model.poll;

import com.agh.surveys.model.user.User;
import com.agh.surveys.model.question.Question;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Poll {

    @Id
    @GeneratedValue
    private Long id;

    private String pollName;
    private LocalDateTime pollCreationTime;
    private LocalDateTime polDeadline;
//    private Long groupId; // TODO dodanie grupy
    @ManyToOne
    private User author;
    @ManyToMany
    private List<Question> questions;

    public Poll(String pollName, LocalDateTime pollCreationTime, LocalDateTime polDeadline, User author, List<Question> questions) {
        this.pollName = pollName;
        this.pollCreationTime = pollCreationTime;
        this.polDeadline = polDeadline;
        this.author = author;
        this.questions = questions;
    }

    public Poll() {
    }
}
