package me.nalab.auth.interceptor;

import me.nalab.core.exception.handler.ErrorTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthExceptionAdvice {

    @ExceptionHandler(CannotValidTokenException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorTemplate handleCannotValidTokenException(CannotValidTokenException exception) {
        return ErrorTemplate.of(exception.getMessage());
    }
}
