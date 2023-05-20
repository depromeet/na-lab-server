package me.nalab.survey.application.exception;

import lombok.Getter;

public class SurveyDoesNotExistException extends RuntimeException {

	@Getter
	private final Long surveyId;

	public SurveyDoesNotExistException(Long surveyId) {
		super("Cannot find any surveyId \"" + surveyId);
		this.surveyId = surveyId;
	}

	@Override
	public synchronized Throwable fillInStackTrace() {
		return this;
	}
}
