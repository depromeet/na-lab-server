package me.nalab.survey.web.adaptor.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import me.nalab.survey.application.exception.TargetDoesNotExistException;
import me.nalab.survey.application.exception.TargetDoesNotHasSurveyException;

@ControllerAdvice
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

}
