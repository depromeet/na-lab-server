package me.nalab.luffy.api.acceptance.test.feedback.create.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class SurveyFindResponse {

	@JsonProperty("survey_id")
	private Long surveyId;

	private TargetResponse target;

	@JsonProperty("question_count")
	private Integer questionCount;

	private List<FormQuestionResponseable> question;
}
