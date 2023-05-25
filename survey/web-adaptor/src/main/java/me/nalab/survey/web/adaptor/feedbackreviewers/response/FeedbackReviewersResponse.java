package me.nalab.survey.web.adaptor.feedbackreviewers.response;

import java.util.List;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@EqualsAndHashCode
public class FeedbackReviewersResponse {

	private final List<FeedbackReviewer> feedbacks;
}
