package com.agh.surveys.model.poll;

import com.agh.surveys.model.group.Group;
import com.agh.surveys.model.user.User;
import com.agh.surveys.model.question.Question;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Poll {

    @Id
    @GeneratedValue
    private Long pollId;

    private String pollName;
    private LocalDateTime pollCreationTime;
    private LocalDateTime polDeadline;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "id" )
    private Group pollGroup;

    @ManyToOne
    @JoinColumn(name = "userNick")
    private User author;

    @OneToMany(mappedBy = "questionPoll", cascade = CascadeType.PERSIST)
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
