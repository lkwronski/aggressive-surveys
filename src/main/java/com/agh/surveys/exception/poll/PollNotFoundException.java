package com.agh.surveys.exception.poll;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No poll found with such ID")
public class PollNotFoundException extends RuntimeException {
}
