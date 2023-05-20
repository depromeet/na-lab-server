package me.nalab.survey.web.adaptor.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import me.nalab.survey.application.exception.EmptySurveyIdListException;
import me.nalab.survey.application.exception.TargetDoesNotExistException;

@RestController
public class SurveyControllerAdvice {

	@ExceptionHandler(EmptySurveyIdListException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	ErrorTemplate handleEmptySurveyIdListException(EmptySurveyIdListException emptySurveyIdListException) {
		return ErrorTemplate.of("Cannot found any survey form");
	}

	@ExceptionHandler(TargetDoesNotExistException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	ErrorTemplate handleTargetDoesNotExistException(TargetDoesNotExistException targetDoesNotExistException) {
		return ErrorTemplate.of("Cannot found target \"" + targetDoesNotExistException.getId() + "\"");
	}

}
