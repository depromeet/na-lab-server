package me.nalab.survey.web.adaptor.createsurvey.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Builder
@ToString
@EqualsAndHashCode
public class SurveyIdResponse {

	@JsonProperty("survey_id")
	private final Long surveyId;

}
