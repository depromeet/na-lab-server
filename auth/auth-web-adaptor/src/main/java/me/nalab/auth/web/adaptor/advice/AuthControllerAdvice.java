package me.nalab.auth.web.adaptor.advice;

import me.nalab.auth.application.common.exception.AuthException;
import me.nalab.core.exception.handler.ErrorTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthControllerAdvice {

    @ExceptionHandler(AuthException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorTemplate handleAuthException(AuthException authException) {
        return ErrorTemplate.of(authException.getMessage());
    }

}
