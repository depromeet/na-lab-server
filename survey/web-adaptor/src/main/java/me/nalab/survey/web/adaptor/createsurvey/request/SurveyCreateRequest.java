package me.nalab.survey.web.adaptor.createsurvey.request;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
	@Min(value = 4, message = "설문지의 질문 갯수는 최소 4개입니다.")
	@Max(value = 24, message = "설문지의 질문 갯수는 최대 24개입니다.")
	private Integer questionCount;

	@Size(min = 4, max = 24, message = "설문지의 설문 갯수는 4에서 24사이가 되어야 합니다.")
	@JsonProperty("question")
	private List<FormQuestionRequestable> formQuestionRequestableList;

}
