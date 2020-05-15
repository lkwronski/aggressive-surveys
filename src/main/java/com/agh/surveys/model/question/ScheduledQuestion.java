package com.agh.surveys.model.question;

import com.agh.surveys.model.poll.Poll;
import com.agh.surveys.model.question.type.QuestionDetails;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class ScheduledQuestion {

    @Id
    @GeneratedValue
    private Integer questionId;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "id")
    private Poll questionPoll;

    @OneToOne(cascade = CascadeType.ALL)
    private QuestionDetails questionDetails;

    public ScheduledQuestion(Poll questionPoll, QuestionDetails questionDetails) {
        this.questionPoll = questionPoll;
        this.questionDetails = questionDetails;
    }
}
