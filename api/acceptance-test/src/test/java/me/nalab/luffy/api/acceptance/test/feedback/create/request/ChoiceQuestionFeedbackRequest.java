package me.nalab.luffy.api.acceptance.test.feedback.create.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
public class ChoiceQuestionFeedbackRequest extends AbstractQuestionFeedbackRequest {

	@JsonProperty("choies")
	private List<Long> choiceList;

}
