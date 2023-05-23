package me.nalab.luffy.api.acceptance.test.feedback.create.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ChoiceResponse {

	@JsonProperty("choice_id")
	private Long choiceId;

	private String content;

	private Integer order;

}
