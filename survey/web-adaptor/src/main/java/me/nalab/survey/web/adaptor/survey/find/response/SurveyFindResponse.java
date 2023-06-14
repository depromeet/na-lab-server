package me.nalab.survey.web.adaptor.survey.find.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
@EqualsAndHashCode
public class SurveyFindResponse {

	@JsonProperty("survey_id")
	private String surveyId;

	private TargetResponse target;

	@JsonProperty("question_count")
	private Integer questionCount;

	private List<FormQuestionResponseable> question;
}
