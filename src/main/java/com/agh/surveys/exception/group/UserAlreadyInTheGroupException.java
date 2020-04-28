package com.agh.surveys.exception.group;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "User is already in the group")
public class UserAlreadyInTheGroupException extends RuntimeException {
}
