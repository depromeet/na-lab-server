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

	private String type;

	@JsonProperty("form_type")
	private String formType;

	private String title;

	private Integer order;

}
