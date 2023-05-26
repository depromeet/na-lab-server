package me.nalab.survey.application.exception;

import lombok.Getter;

public class SurveyDoesNotHasTargetException extends RuntimeException {

	@Getter
	private final Long surveyId;

	public SurveyDoesNotHasTargetException(Long surveyId) {
		super("Cannot find any targetId from survey \"" + surveyId + "\"");
		this.surveyId = surveyId;
	}

	@Override
	public synchronized Throwable fillInStackTrace() {
		return this;
	}
}
