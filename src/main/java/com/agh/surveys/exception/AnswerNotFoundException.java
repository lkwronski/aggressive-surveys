package com.agh.surveys.exception;

public class AnswerNotFoundException extends RuntimeException {

    public AnswerNotFoundException(Long answerId){
        super("Could not find answer " + answerId);
    }
}
