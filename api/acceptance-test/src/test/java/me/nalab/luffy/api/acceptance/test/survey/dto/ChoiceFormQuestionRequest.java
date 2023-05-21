package me.nalab.luffy.api.acceptance.test.survey.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class ChoiceFormQuestionRequest extends FormQuestionRequestable {

	@JsonProperty("max_selectable_count")
	private Integer maxSelectableCount;

	@JsonProperty("form_type")
	private String choiceFormQuestionType;

	@JsonProperty("choices")
	private List<ChoiceRequest> choiceRequestList;

}
