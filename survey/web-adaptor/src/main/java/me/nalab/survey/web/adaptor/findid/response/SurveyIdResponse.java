package me.nalab.survey.web.adaptor.findid.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SurveyIdResponse {

	@JsonProperty("survey_id")
	private final String surveyId;

}
