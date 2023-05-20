package me.nalab.survey.application.exception;

import lombok.Getter;

public class EmptySurveyIdListException extends RuntimeException {

	@Getter
	private final Long targetId;

	public EmptySurveyIdListException(Long targetId) {
		super("Cannot find any surveyId from target \"" + targetId + "\"");
		this.targetId = targetId;
	}

	@Override
	public synchronized Throwable fillInStackTrace() {
		return this;
	}

}
