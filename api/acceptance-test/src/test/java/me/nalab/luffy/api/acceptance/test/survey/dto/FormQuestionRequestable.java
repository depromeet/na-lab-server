package me.nalab.luffy.api.acceptance.test.survey.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public abstract class FormQuestionRequestable {

	protected String title;

	protected Integer order;

	@JsonProperty("type")
	protected String questionType;

}
