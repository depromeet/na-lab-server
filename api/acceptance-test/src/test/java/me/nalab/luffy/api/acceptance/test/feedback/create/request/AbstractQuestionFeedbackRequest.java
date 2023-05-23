package me.nalab.luffy.api.acceptance.test.feedback.create.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
public abstract class AbstractQuestionFeedbackRequest {

	@JsonProperty("question_id")
	protected Long questionId;
	protected String type;

}
