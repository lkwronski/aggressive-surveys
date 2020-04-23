package com.agh.surveys.exception.poll;

public class PollNotFoundException extends RuntimeException {

    public PollNotFoundException(Long id){
        super("Could not find poll " + id);
    }
}
