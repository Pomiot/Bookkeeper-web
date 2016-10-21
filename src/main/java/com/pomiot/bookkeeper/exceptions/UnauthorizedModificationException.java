package com.pomiot.bookkeeper.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Resource modification not allowed.")
public class UnauthorizedModificationException extends RuntimeException {
}
