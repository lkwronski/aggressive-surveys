package com.agh.surveys.model.answer;


import com.agh.surveys.model.answer.type.AnswerDetails;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
public class Answer {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Long questionID;
    @ManyToMany
    private Long answerAutorID;
    private LocalDateTime answerDate;
    @OneToOne(cascade = CascadeType.ALL)
    private AnswerDetails answerDetails;

    public Answer(Long questionID, Long answerAutorID, LocalDateTime answerDate, AnswerDetails answerDetails) {
        this.questionID = questionID;
        this.answerAutorID = answerAutorID;
        this.answerDate = answerDate;
        this.answerDetails = answerDetails;
    }

    public Answer() {}
}
