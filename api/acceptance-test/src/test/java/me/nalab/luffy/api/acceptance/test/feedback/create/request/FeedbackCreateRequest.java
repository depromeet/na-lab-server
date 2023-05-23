package me.nalab.luffy.api.acceptance.test.feedback.create.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackCreateRequest {

	@JsonProperty("reviewer")
	private ReviewerRequest reviewerRequest;

	@JsonProperty("question_feedback")
	private List<AbstractQuestionFeedbackRequest> abstractQuestionFeedbackRequests;

}
