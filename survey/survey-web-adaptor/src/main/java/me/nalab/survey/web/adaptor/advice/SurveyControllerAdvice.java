package me.nalab.survey.web.adaptor.advice;

import me.nalab.survey.application.exception.DuplicateSurveyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import me.nalab.survey.application.exception.SurveyDoesNotExistException;
import me.nalab.survey.application.exception.TargetDoesNotExistException;
import me.nalab.survey.application.exception.TargetDoesNotHasSurveyException;

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

	@ExceptionHandler(SurveyDoesNotExistException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	ErrorTemplate handleSurveyDoesNotExistException(SurveyDoesNotExistException surveyDoesNotExistException) {
		return ErrorTemplate.of(
			"Cannot found any survey form id \"" + surveyDoesNotExistException.getSurveyId() + "\"");
	}

	@ExceptionHandler(DuplicateSurveyException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorTemplate handleDuplicateSurveyException(DuplicateSurveyException duplicateSurveyException) {
		return ErrorTemplate.of(duplicateSurveyException.getMessage());
	}

}
