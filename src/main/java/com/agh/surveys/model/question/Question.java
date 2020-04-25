package com.agh.surveys.model.question;


import com.agh.surveys.model.poll.Poll;
import com.agh.surveys.model.question.type.QuestionDetails;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
public class Question {

    @Id
    @GeneratedValue
    private Integer questionId;

//    @ToString.Exclude
//    @ManyToOne
//    @JoinColumn(name = "id")
//    private Poll questionPoll;

    @OneToOne(cascade = CascadeType.ALL)
    private QuestionDetails questionDetails;

    public Question() {

    }

    public Question(QuestionDetails questionDetails) {
        this.questionDetails = questionDetails;
    }
}
