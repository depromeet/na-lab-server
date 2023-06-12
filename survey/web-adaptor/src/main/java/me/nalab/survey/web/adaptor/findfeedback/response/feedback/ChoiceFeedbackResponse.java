package me.nalab.survey.web.adaptor.findfeedback.response.feedback;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString
@SuperBuilder
public class ChoiceFeedbackResponse extends AbstractFeedbackResponse {

	@JsonProperty("choice_id")
	private final Set<String> choiceIdSet;

}
