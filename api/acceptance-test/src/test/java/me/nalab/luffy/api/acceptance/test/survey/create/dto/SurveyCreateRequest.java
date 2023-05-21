package me.nalab.luffy.api.acceptance.test.survey.create.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class SurveyCreateRequest {

	@JsonProperty("question_count")
	private Integer questionCount;

	@JsonProperty("question")
	private List<FormQuestionRequestable> formQuestionRequestableList;

}
