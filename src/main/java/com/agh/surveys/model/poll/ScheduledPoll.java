package com.agh.surveys.model.poll;


import com.agh.surveys.model.group.Group;
import com.agh.surveys.model.question.ScheduledQuestion;
import com.agh.surveys.model.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "scheduled_poll_entity")
public class ScheduledPoll {

    @Id
    @GeneratedValue
    private Integer pollId;

    @Column(name = "name")
    private String pollName;

    @Column(name = "creation_time")
    private LocalDateTime pollCreationTime;

    @Column(name = "interval")
    private Duration scheduleInterval;

    @Column(name = "deadline")
    private Duration scheduledDeadline;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "id")
    private Group pollGroup;

    @ManyToOne
    @JoinColumn(name = "userNick")
    private User author;

    @OneToMany(mappedBy = "questionPoll", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ScheduledQuestion> questions;

    public ScheduledPoll(String pollName, LocalDateTime pollCreationTime, Duration deadline, Duration scheduleInterval, User author, List<ScheduledQuestion> questions) {
        this.pollName = pollName;
        this.pollCreationTime = pollCreationTime;
        this.scheduledDeadline = deadline;
        this.scheduleInterval = scheduleInterval;
        this.author = author;
        this.questions = questions;
    }


}
