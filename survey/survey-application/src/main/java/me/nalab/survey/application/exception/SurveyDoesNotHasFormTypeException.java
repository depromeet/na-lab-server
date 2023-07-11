package me.nalab.survey.application.exception;

import lombok.Getter;

public class SurveyDoesNotHasFormTypeException extends RuntimeException {

	@Getter
	private final Long surveyId;

	@Getter
	private final String formType;

	public SurveyDoesNotHasFormTypeException(Long surveyId, String formType) {
		super("Cannot find form question surveyId \"" + surveyId + " formType\"" + formType + "\"");
		this.surveyId = surveyId;
		this.formType = formType;
	}
}
