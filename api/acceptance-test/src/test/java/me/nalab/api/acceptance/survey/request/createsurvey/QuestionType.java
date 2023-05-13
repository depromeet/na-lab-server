package me.nalab.api.acceptance.survey.request.createsurvey;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum QuestionType {

	CHOICE("choice"),
	SHORT("short");

	private final String lowerCase;

}
