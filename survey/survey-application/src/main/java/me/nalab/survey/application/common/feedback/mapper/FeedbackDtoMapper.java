package me.nalab.survey.application.common.feedback.mapper;

import java.util.List;
import java.util.stream.Collectors;

import me.nalab.survey.application.common.feedback.dto.BookmarkDto;
import me.nalab.survey.application.common.feedback.dto.ChoiceFormQuestionFeedbackDto;
import me.nalab.survey.application.common.feedback.dto.FeedbackDto;
import me.nalab.survey.application.common.feedback.dto.FormQuestionFeedbackDtoable;
import me.nalab.survey.application.common.feedback.dto.ReviewerDto;
import me.nalab.survey.application.common.feedback.dto.ShortFormQuestionFeedbackDto;
import me.nalab.survey.domain.feedback.Bookmark;
import me.nalab.survey.domain.feedback.ChoiceFormQuestionFeedback;
import me.nalab.survey.domain.feedback.Feedback;
import me.nalab.survey.domain.feedback.FormQuestionFeedbackable;
import me.nalab.survey.domain.feedback.Reviewer;
import me.nalab.survey.domain.feedback.ShortFormQuestionFeedback;
import me.nalab.survey.domain.survey.Survey;

public final class FeedbackDtoMapper {

	private FeedbackDtoMapper() {
		throw new UnsupportedOperationException("Cannot invoke constructor \"FeedbackDtoMapper()\"");
	}

	public static Feedback toDomain(Survey survey, FeedbackDto feedbackDto) {
		return Feedback.builder()
			.surveyId(survey.getId())
			.reviewer(getReviewer(feedbackDto.getReviewerDto()))
			.formQuestionFeedbackableList(getFormQuestionFeedbackList(feedbackDto))
			.createdAt(feedbackDto.getCreatedAt())
			.updatedAt(feedbackDto.getUpdatedAt())
			.isRead(feedbackDto.isRead())
			.build();
	}

	private static Reviewer getReviewer(ReviewerDto reviewerDto) {
		return Reviewer.builder()
			.position(reviewerDto.getPosition())
			.collaborationExperience(reviewerDto.isCollaborationExperience())
			.build();
	}

	private static List<FormQuestionFeedbackable> getFormQuestionFeedbackList(FeedbackDto feedbackDto) {
		return feedbackDto.getFormQuestionFeedbackDtoableList().stream().map(q -> {
			if (q instanceof ShortFormQuestionFeedbackDto) {
				return getShortFormQuestionFeedback((ShortFormQuestionFeedbackDto)q);
			}
			return getChoiceFormQuestionFeedback((ChoiceFormQuestionFeedbackDto)q);
		}).collect(Collectors.toList());
	}

	private static ShortFormQuestionFeedback getShortFormQuestionFeedback(
		ShortFormQuestionFeedbackDto shortFormQuestionFeedbackDto) {
		return ShortFormQuestionFeedback.builder()
			.questionId(shortFormQuestionFeedbackDto.getQuestionId())
			.bookmark(Bookmark.builder()
				.isBookmarked(shortFormQuestionFeedbackDto.getBookmarkDto().isBookmarked())
				.bookmarkedAt(shortFormQuestionFeedbackDto.getBookmarkDto().getBookmarkedAt())
				.build())
			.isRead(shortFormQuestionFeedbackDto.isRead())
			.replyList(shortFormQuestionFeedbackDto.getReplyList())
			.build();
	}

	private static ChoiceFormQuestionFeedback getChoiceFormQuestionFeedback(
		ChoiceFormQuestionFeedbackDto choiceFormQuestionFeedbackDto) {
		return ChoiceFormQuestionFeedback.builder()
			.questionId(choiceFormQuestionFeedbackDto.getQuestionId())
			.bookmark(Bookmark.builder()
				.isBookmarked(choiceFormQuestionFeedbackDto.getBookmarkDto().isBookmarked())
				.bookmarkedAt(choiceFormQuestionFeedbackDto.getBookmarkDto().getBookmarkedAt())
				.build())
			.isRead(choiceFormQuestionFeedbackDto.isRead())
			.selectedChoiceIdSet(choiceFormQuestionFeedbackDto.getSelectedChoiceIdSet())
			.build();
	}

	public static FeedbackDto toDto(Feedback feedback) {
		return FeedbackDto.builder()
			.id(feedback.getId())
			.surveyId(feedback.getSurveyId())
			.reviewerDto(getReviewerDto(feedback.getReviewer()))
			.formQuestionFeedbackDtoableList(getFormQuestionFeedbackDtoList(feedback))
			.createdAt(feedback.getCreatedAt())
			.updatedAt(feedback.getUpdatedAt())
			.isRead(feedback.isRead())
			.build();
	}

	private static ReviewerDto getReviewerDto(Reviewer reviewer) {
		return ReviewerDto.builder()
			.id(reviewer.getId())
			.nickName(reviewer.getNickName())
			.position(reviewer.getPosition())
			.collaborationExperience(reviewer.isCollaborationExperience())
			.build();
	}

	private static List<FormQuestionFeedbackDtoable> getFormQuestionFeedbackDtoList(Feedback feedback) {
		return feedback.getFormQuestionFeedbackableList()
					   .stream()
					   .map(FeedbackDtoMapper::getFormQuestionFeedbackDtoable)
					   .collect(Collectors.toList());
	}

	private static FormQuestionFeedbackDtoable getFormQuestionFeedbackDtoable(FormQuestionFeedbackable q) {
		if (q instanceof ShortFormQuestionFeedback) {
			return getShortFormQuestionFeedbackDto((ShortFormQuestionFeedback) q);
		}
		return getChoiceFormQuestionFeedbackDto((ChoiceFormQuestionFeedback) q);
	}

	private static ShortFormQuestionFeedbackDto getShortFormQuestionFeedbackDto(
		ShortFormQuestionFeedback shortFormQuestionFeedback) {
		return ShortFormQuestionFeedbackDto.builder()
			.id(shortFormQuestionFeedback.getId())
			.questionId(shortFormQuestionFeedback.getQuestionId())
			.isRead(shortFormQuestionFeedback.isRead())
			.bookmarkDto(BookmarkDto.builder()
				.isBookmarked(shortFormQuestionFeedback.getBookmark().isBookmarked())
				.bookmarkedAt(shortFormQuestionFeedback.getBookmark().getBookmarkedAt())
				.build())
			.replyList(shortFormQuestionFeedback.getReplyList())
			.build();
	}

	private static ChoiceFormQuestionFeedbackDto getChoiceFormQuestionFeedbackDto(
		ChoiceFormQuestionFeedback choiceFormQuestionFeedback) {
		return ChoiceFormQuestionFeedbackDto.builder()
			.id(choiceFormQuestionFeedback.getId())
			.questionId(choiceFormQuestionFeedback.getQuestionId())
			.isRead(choiceFormQuestionFeedback.isRead())
			.bookmarkDto(BookmarkDto.builder()
				.isBookmarked(choiceFormQuestionFeedback.getBookmark().isBookmarked())
				.bookmarkedAt(choiceFormQuestionFeedback.getBookmark().getBookmarkedAt())
				.build())
			.selectedChoiceIdSet(choiceFormQuestionFeedback.getSelectedChoiceIdSet())
			.build();
	}

	public static FeedbackDto toDtoWithBookmarkedForm(Feedback feedback) {
		return FeedbackDto.builder()
						  .id(feedback.getId())
						  .surveyId(feedback.getSurveyId())
						  .reviewerDto(getReviewerDto(feedback.getReviewer()))
						  .formQuestionFeedbackDtoableList(getBookmakredFormQuestionFeedbackDtoList(feedback))
						  .createdAt(feedback.getCreatedAt())
						  .updatedAt(feedback.getUpdatedAt())
						  .isRead(feedback.isRead())
						  .build();
	}

	private static List<FormQuestionFeedbackDtoable> getBookmakredFormQuestionFeedbackDtoList(Feedback feedback) {
		return feedback.getFormQuestionFeedbackableList()
					   .stream()
					   .filter(FormQuestionFeedbackable::isBookmarked)
					   .map(FeedbackDtoMapper::getFormQuestionFeedbackDtoable)
					   .collect(Collectors.toList());
	}

}
