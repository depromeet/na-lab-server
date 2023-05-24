package me.nalab.survey.web.adaptor.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import me.nalab.survey.application.exception.TargetDoesNotExistException;
import me.nalab.survey.application.exception.TargetDoesNotHasSurveyException;
import me.nalab.survey.domain.exception.IllegalFeedbackException;
import me.nalab.survey.domain.exception.IllegalQuestionFeedbackException;

@RestControllerAdvice
public class SurveyControllerAdvice {

	@ExceptionHandler(TargetDoesNotHasSurveyException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	ErrorTemplate handleEmptySurveyIdListException(TargetDoesNotHasSurveyException targetDoesNotHasSurveyException) {
		return ErrorTemplate.of("Cannot found any survey form");
	}

	@ExceptionHandler(TargetDoesNotExistException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	ErrorTemplate handleTargetDoesNotExistException(TargetDoesNotExistException targetDoesNotExistException) {
		return ErrorTemplate.of("Cannot found target \"" + targetDoesNotExistException.getId() + "\"");
	}

	@ExceptionHandler({IllegalFeedbackException.class, IllegalQuestionFeedbackException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	ErrorTemplate handleFeedbackException(RuntimeException runtimeException) {
		return ErrorTemplate.of("Illegal feedback request");
	}

}
