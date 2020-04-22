package com.agh.surveys.exception.answer;

public class AnswerNotFoundException extends RuntimeException {

    public AnswerNotFoundException(Long answerId){
        super("Could not find answer " + answerId);
    }
}
