package me.nalab.survey.web.adaptor.findfeedback.response.survey;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import me.nalab.survey.web.adaptor.findfeedback.response.feedback.ShortFeedbackResponse;

@Getter
@ToString
@SuperBuilder
public class ShortSurveyResponse extends AbstractSurveyResponse {

	@JsonProperty("feedbacks")
	private final List<ShortFeedbackResponse> shortFeedbackResponseList;

}
