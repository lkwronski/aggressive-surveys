package com.agh.surveys.exception.group;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No group found with such ID")
public class GroupNotFoundException extends RuntimeException {
}
