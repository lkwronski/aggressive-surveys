package com.agh.surveys.exception.group;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "No such user in the group")
public class UserNotInTheGroupException extends RuntimeException {
}
