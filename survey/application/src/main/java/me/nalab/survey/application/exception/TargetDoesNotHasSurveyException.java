package me.nalab.survey.application.exception;

import lombok.Getter;

public class TargetDoesNotHasSurveyException extends RuntimeException {

	@Getter
	private final Long targetId;

	public TargetDoesNotHasSurveyException(Long targetId) {
		super("Cannot find any surveyId from target \"" + targetId + "\"");
		this.targetId = targetId;
	}

	@Override
	public synchronized Throwable fillInStackTrace() {
		return this;
	}

}
