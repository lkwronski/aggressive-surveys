package com.agh.surveys.model.answer;


import com.agh.surveys.model.answer.type.AnswerDetails;
import com.agh.surveys.model.question.Question;
import com.agh.surveys.model.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class Answer {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @ManyToOne
    @Column(name = "question")
    private Question question;

    @ManyToOne
    @Column(name = "author")
    private User answerAuthor;

    @Column(name = "create_date")
    private LocalDateTime answerDate;
    
    @OneToOne(cascade = CascadeType.ALL)
    @Column(name = "details")
    private AnswerDetails answerDetails;

    public Answer(Question question, User answerAuthor, LocalDateTime answerDate, AnswerDetails answerDetails) {
        this.question = question;
        this.answerAuthor = answerAuthor;
        this.answerDate = answerDate;
        this.answerDetails = answerDetails;
    }

}
