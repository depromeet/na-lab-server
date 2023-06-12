package me.nalab.survey.web.adaptor.feedbackreviewers;

import java.util.List;
import java.util.stream.Collectors;

import me.nalab.survey.application.common.feedback.dto.FeedbackDto;
import me.nalab.survey.web.adaptor.feedbackreviewers.response.FeedbackReviewer;
import me.nalab.survey.web.adaptor.feedbackreviewers.response.FeedbackReviewersResponse;
import me.nalab.survey.web.adaptor.feedbackreviewers.response.ReviewerResponse;

class FeedbackReviewersResponseMapper {

	private FeedbackReviewersResponseMapper() {
		throw new UnsupportedOperationException("Cannot invoke constructor \"FeedbackReviewersResponseMapper()\"");
	}
	static FeedbackReviewersResponse toFeedbackReviewersResponse(List<FeedbackDto> feedbacks) {
		List<FeedbackReviewer> feedbackReviewers = feedbacks.stream()
			.map(it -> FeedbackReviewer.builder()
				.feedbackId(String.valueOf(it.getId()))
				.createdAt(it.getCreatedAt())
				.reviewer(ReviewerResponse.builder()
					.nickName(it.getReviewerDto().getNickName())
					.collaborationExperience(it.getReviewerDto().isCollaborationExperience())
					.position(it.getReviewerDto().getPosition())
					.build())
				.isRead(it.isRead())
				.build())
			.collect(Collectors.toList());
		return FeedbackReviewersResponse.builder().feedbacks(feedbackReviewers).build();
	}
}
