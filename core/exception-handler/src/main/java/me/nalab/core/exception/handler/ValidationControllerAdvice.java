package me.nalab.core.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * javax.validation 어노테이션의 message를 잡아 내려줍니다
 */
@RestControllerAdvice
public class ValidationControllerAdvice {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorTemplate catchValidationException(MethodArgumentNotValidException methodArgumentNotValidException) {
		String messages = methodArgumentNotValidException
			.getBindingResult()
			.getAllErrors()
			.get(0)
			.getDefaultMessage();
		return ErrorTemplate.of(messages);
	}

}
