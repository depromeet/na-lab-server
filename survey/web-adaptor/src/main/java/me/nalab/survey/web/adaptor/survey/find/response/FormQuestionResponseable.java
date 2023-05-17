package me.nalab.survey.web.adaptor.survey.find.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@ToString
@EqualsAndHashCode
public abstract class FormQuestionResponseable {

	@JsonProperty("question_id")
	private Long questionId;

	private QuestionResponseType type;

	private String title;

	private Integer order;

}
