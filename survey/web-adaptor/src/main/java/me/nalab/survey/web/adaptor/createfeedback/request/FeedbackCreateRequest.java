package me.nalab.survey.web.adaptor.createfeedback.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class FeedbackCreateRequest {

	@JsonProperty("reviewer")
	private ReviewerRequest reviewerRequest;

	@JsonProperty("question_feedback")
	private List<AbstractQuestionFeedbackRequest> abstractQuestionFeedbackRequestList;

}
