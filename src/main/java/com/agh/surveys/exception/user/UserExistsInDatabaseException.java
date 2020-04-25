package com.agh.surveys.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "User already exists")
public class UserExistsInDatabaseException extends RuntimeException {
}
