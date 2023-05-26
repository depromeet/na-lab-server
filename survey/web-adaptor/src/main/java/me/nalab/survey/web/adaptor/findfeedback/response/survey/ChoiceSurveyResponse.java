package me.nalab.survey.web.adaptor.findfeedback.response.survey;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import me.nalab.survey.web.adaptor.findfeedback.response.feedback.ChoiceFeedbackResponse;

@Getter
@ToString
@SuperBuilder
public class ChoiceSurveyResponse extends AbstractSurveyResponse {

	@JsonProperty("choices")
	private final List<QuestionResponse> choiceResponseList;

	@JsonProperty("feedbacks")
	private final List<ChoiceFeedbackResponse> choiceFeedbackResponseList;

	@Getter
	@Builder
	@ToString
	public static final class QuestionResponse {
		@JsonProperty("choice_id")
		private final Long choiceId;
		private final Integer order;
		private final String content;
	}

}
