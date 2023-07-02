package me.nalab.survey.web.adaptor.survey.find.response;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonProperty;

@Builder
@Getter
@ToString
@EqualsAndHashCode
public class ChoiceResponse {

	@JsonProperty("choice_id")
	private String choiceId;

	private String content;

	private Integer order;

}
