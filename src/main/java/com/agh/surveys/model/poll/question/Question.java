package com.agh.surveys.model.poll.question;


import com.agh.surveys.model.poll.question.type.QuestionDetails;
import com.agh.surveys.model.poll.question.type.QuestionType;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Question {

    @Id
    @GeneratedValue
    private Long id;
    private Long pollId;

    @OneToOne(cascade = CascadeType.ALL)
    private QuestionDetails questionDetails;

    public Question() {

    }

    public Question(Long pollId, QuestionDetails questionDetails) {
        this.pollId = pollId;
        this.questionDetails = questionDetails;
    }
}
