package me.nalab.survey.web.adaptor.findspecific.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
@EqualsAndHashCode
public class ChoiceResponse {

	@JsonProperty("choice_id")
	private final String choiceId;

	private final String content;

	private final int order;
}
