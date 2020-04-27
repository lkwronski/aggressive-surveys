package com.agh.surveys.model.question;


import com.agh.surveys.model.answer.Answer;
import com.agh.surveys.model.poll.Poll;
import com.agh.surveys.model.question.type.QuestionDetails;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Question {

    @Id
    @GeneratedValue
    private Integer questionId;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "id")
    private Poll questionPoll;

    @OneToOne(cascade = CascadeType.ALL)
    private QuestionDetails questionDetails;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answer> answers;

    public Question(Poll questionPoll, QuestionDetails questionDetails, List<Answer> answers) {
        this.questionPoll = questionPoll;
        this.questionDetails = questionDetails;
        this.answers = answers;
    }

    public Question(Poll questionPoll, QuestionDetails questionDetails) {
        this.questionPoll = questionPoll;
        this.questionDetails = questionDetails;
        this.answers = new LinkedList<>();
    }
}
