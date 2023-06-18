package me.nalab.core.authorization.aop.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import me.nalab.core.authorization.api.CannotAuthorizationException;

@RestControllerAdvice
public class AuthorizationExceptionAdvice {

	@ExceptionHandler(CannotAuthorizationException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	ErrorTemplate handleCannotAuthorizationException(CannotAuthorizationException exception) {
		return ErrorTemplate.of("Invalid token cause \"" + exception.getMessage() + "\"");
	}

}
