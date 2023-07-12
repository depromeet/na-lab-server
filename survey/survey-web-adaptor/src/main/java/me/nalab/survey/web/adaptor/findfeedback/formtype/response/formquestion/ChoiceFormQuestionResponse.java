package me.nalab.survey.web.adaptor.findfeedback.formtype.response.formquestion;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString
@SuperBuilder
public class ChoiceFormQuestionResponse extends AbstractFormQuestionResponse {

	@JsonProperty("choices")
	private final List<ChoiceResponse> choiceResponseList;

}
