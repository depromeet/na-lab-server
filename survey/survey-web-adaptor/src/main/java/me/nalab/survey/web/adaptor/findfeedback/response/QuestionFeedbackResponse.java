package me.nalab.survey.web.adaptor.findfeedback.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import me.nalab.survey.web.adaptor.findfeedback.response.survey.AbstractSurveyResponse;

@Getter
@Builder
@ToString
public class QuestionFeedbackResponse {

	@JsonProperty("question_feedback")
	private final List<AbstractSurveyResponse> abstractSurveyResponse;

}
