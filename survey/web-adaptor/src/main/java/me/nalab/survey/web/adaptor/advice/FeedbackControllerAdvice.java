package me.nalab.survey.web.adaptor.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import me.nalab.survey.domain.exception.IllegalFeedbackException;
import me.nalab.survey.domain.exception.IllegalQuestionFeedbackException;

@RestControllerAdvice
public class FeedbackControllerAdvice {

	@ExceptionHandler({IllegalFeedbackException.class, IllegalQuestionFeedbackException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	ErrorTemplate handleFeedbackException(RuntimeException runtimeException) {
		return ErrorTemplate.of("Illegal feedback request");
	}

}
