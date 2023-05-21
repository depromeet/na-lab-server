package me.nalab.luffy.api.acceptance.test.survey.create.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class ShortFormQuestionRequest extends FormQuestionRequestable {

	@JsonProperty("form_type")
	private String shortFormQuestionType;

}
