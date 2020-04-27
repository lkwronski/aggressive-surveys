package com.agh.surveys.exception.answer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No answer found with such ID")
public class AnswerNotFoundException extends RuntimeException {
}
