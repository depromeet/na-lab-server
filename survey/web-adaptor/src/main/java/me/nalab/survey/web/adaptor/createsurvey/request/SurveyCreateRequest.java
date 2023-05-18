package me.nalab.survey.web.adaptor.createsurvey.request;

import java.util.List;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class SurveyCreateRequest {

	@JsonProperty("question_count")
	private Integer questionCount;

	@Size(min = 5, max = 25, message = "설문지의 설문 갯수는 5에서 25사이가 되어야 합니다.")
	@JsonProperty("question")
	private List<FormQuestionRequestable> formQuestionRequestableList;

}
