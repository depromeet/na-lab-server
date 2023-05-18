package me.nalab.survey.web.adaptor.createsurvey.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class SurveyIdResponse {

	@JsonProperty("survey_id")
	private final Long surveyId;

}
