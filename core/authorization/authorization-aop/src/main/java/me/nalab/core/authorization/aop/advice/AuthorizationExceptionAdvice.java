package me.nalab.core.authorization.aop.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import me.nalab.core.authorization.api.CannotAuthorizationException;

@Slf4j
@RestControllerAdvice
public class AuthorizationExceptionAdvice {

	@ExceptionHandler(CannotAuthorizationException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	ErrorTemplate handleCannotAuthorizationException(CannotAuthorizationException exception) {
		log.error(exception.getMessage(), exception);
		return ErrorTemplate.of("Invalid token cause \"" + exception.getMessage() + "\"");
	}

}
