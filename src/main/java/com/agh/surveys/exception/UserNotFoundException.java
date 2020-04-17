package com.agh.surveys.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No user with such nick")
public class UserNotFoundException extends RuntimeException {
}
