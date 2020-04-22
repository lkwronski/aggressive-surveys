package com.agh.surveys.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Could not find question")
public class QuestionNotFoundException extends RuntimeException {
}
