package me.nalab.survey.jpa.adaptor.exception.survey;

public class SurveyDoesNotExistException extends RuntimeException {

	public SurveyDoesNotExistException(Long surveyId) {
		super("Cannot find Survey where surveyId \"" + surveyId + "\"");
	}
}
