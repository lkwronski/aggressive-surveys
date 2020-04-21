package com.agh.surveys.model.question;


import com.agh.surveys.model.question.type.QuestionDetails;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Question {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private QuestionDetails questionDetails;

    public Question() {

    }

    public Question(QuestionDetails questionDetails) {
        this.questionDetails = questionDetails;
    }
}
