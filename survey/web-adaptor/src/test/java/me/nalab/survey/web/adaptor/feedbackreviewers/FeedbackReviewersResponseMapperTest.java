package me.nalab.survey.web.adaptor.feedbackreviewers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

import me.nalab.survey.application.common.feedback.dto.ChoiceFormQuestionFeedbackDto;
import me.nalab.survey.application.common.feedback.dto.FeedbackDto;
import me.nalab.survey.application.common.feedback.dto.ReviewerDto;
import me.nalab.survey.application.common.feedback.dto.ShortFormQuestionFeedbackDto;
import me.nalab.survey.web.adaptor.feedbackreviewers.response.FeedbackReviewer;
import me.nalab.survey.web.adaptor.feedbackreviewers.response.FeedbackReviewersResponse;

class FeedbackReviewersResponseMapperTest {

	@Test
	void feedbackReviewersResponseMapperTest() {

		FeedbackDto feedbackDto = getFeedbackDto();

		FeedbackReviewersResponse response = FeedbackReviewersResponseMapper.toFeedbackReviewersResponse(
			List.of(feedbackDto));
		FeedbackReviewer feedbackReviewer = response.getFeedbacks().get(0);

		assertAll(
			() -> assertThat(response.getFeedbacks()).hasSize(1),
			() -> assertThat(feedbackReviewer.getFeedbackId()).isEqualTo(1L),
			() -> assertThat(feedbackReviewer.getIsRead()).isTrue(),
			() -> assertThat(feedbackReviewer.getReviewer().getNickname()).isEqualTo("sujin"),
			() -> assertThat(feedbackReviewer.getReviewer().getCollaborationExperience()).isTrue(),
			() -> assertThat(feedbackReviewer.getReviewer().getPosition()).isEqualTo("developer")
		);

	}

	private static FeedbackDto getFeedbackDto() {
		return FeedbackDto.builder()
			.id(1L)
			.surveyId(3L)
			.formQuestionFeedbackDtoableList(
				List.of(ChoiceFormQuestionFeedbackDto.builder()
						.id(1L)
						.questionId(1L)
						.isRead(true)
						.selectedChoiceIdSet(new HashSet<>(1, 2))
						.build(),
					ShortFormQuestionFeedbackDto.builder()
						.id(2L)
						.questionId(2L)
						.isRead(true)
						.replyList(List.of("reply1", "reply2"))
						.build()))
			.isRead(true)
			.reviewerDto(ReviewerDto.builder()
				.id(34L)
				.nickName("sujin")
				.collaborationExperience(true)
				.position("developer")
				.build())
			.createdAt(LocalDateTime.of(2023, 5, 26, 6, 46, 0))
			.updatedAt(LocalDateTime.of(2023, 5, 26, 6, 46, 0))
			.build();
	}
}
