package com.agh.surveys.model.poll.question.repository;

public class QuestionNotFoundException extends RuntimeException {

    public QuestionNotFoundException(Long id){
        super("Could not find question " + id);
    }
}
