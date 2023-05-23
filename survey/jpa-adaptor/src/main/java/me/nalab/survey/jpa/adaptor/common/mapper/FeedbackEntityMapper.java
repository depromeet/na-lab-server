package me.nalab.survey.jpa.adaptor.common.mapper;

import java.util.List;
import java.util.stream.Collectors;

import me.nalab.core.data.feedback.ChoiceFormFeedbackEntity;
import me.nalab.core.data.feedback.FeedbackEntity;
import me.nalab.core.data.feedback.FormFeedbackEntity;
import me.nalab.core.data.feedback.ReviewerEntity;
import me.nalab.core.data.feedback.ShortFormFeedbackEntity;
import me.nalab.survey.domain.feedback.ChoiceFormQuestionFeedback;
import me.nalab.survey.domain.feedback.Feedback;
import me.nalab.survey.domain.feedback.FormQuestionFeedbackable;
import me.nalab.survey.domain.feedback.Reviewer;
import me.nalab.survey.domain.feedback.ShortFormQuestionFeedback;

public final class FeedbackEntityMapper {

	private FeedbackEntityMapper() {
		throw new IllegalStateException("Cannot invoke constructor \"FeedbackEntityMapper()\"");
	}

	public static Feedback toDomain(FeedbackEntity feedbackEntity) {
		return Feedback.builder()
			.id(feedbackEntity.getId())
			.surveyId(feedbackEntity.getSurveyId())
			.reviewer(getReviewer(feedbackEntity.getReviewer()))
			.formQuestionFeedbackableList(getFormQuestionFeedbackList(feedbackEntity))
			.createdAt(feedbackEntity.getCreatedAt())
			.updatedAt(feedbackEntity.getUpdatedAt())
			.isRead(feedbackEntity.isRead())
			.build();
	}

	private static Reviewer getReviewer(ReviewerEntity reviewerEntity) {
		return Reviewer.builder()
			.id(reviewerEntity.getId())
			.nickName(reviewerEntity.getNickName())
			.position(reviewerEntity.getPosition())
			.collaborationExperience(reviewerEntity.isCollaborationExperience())
			.build();
	}

	private static List<FormQuestionFeedbackable> getFormQuestionFeedbackList(FeedbackEntity feedbackEntity) {
		return feedbackEntity.getFormFeedbackEntityList().stream().map(q -> {
			if(q instanceof ShortFormFeedbackEntity) {
				return getShortFormQuestionFeedback((ShortFormFeedbackEntity)q);
			}
			return getChoiceFormQuestionFeedback((ChoiceFormFeedbackEntity)q);
		}).collect(Collectors.toList());
	}

	private static ShortFormQuestionFeedback getShortFormQuestionFeedback(
		ShortFormFeedbackEntity shortFormFeedbackEntity) {
		return ShortFormQuestionFeedback.builder()
			.id(shortFormFeedbackEntity.getId())
			.questionId(shortFormFeedbackEntity.getQuestionId())
			.isRead(shortFormFeedbackEntity.isRead())
			.replyList(shortFormFeedbackEntity.getReplyList())
			.build();
	}

	private static ChoiceFormQuestionFeedback getChoiceFormQuestionFeedback(
		ChoiceFormFeedbackEntity choiceFormFeedbackEntity) {
		return ChoiceFormQuestionFeedback.builder()
			.id(choiceFormFeedbackEntity.getId())
			.questionId(choiceFormFeedbackEntity.getQuestionId())
			.isRead(choiceFormFeedbackEntity.isRead())
			.selectedChoiceIdSet(choiceFormFeedbackEntity.getSelectSet())
			.build();
	}

	public static FeedbackEntity toEntity(Feedback feedback) {
		return FeedbackEntity.builder()
			.id(feedback.getId())
			.surveyId(feedback.getSurveyId())
			.reviewer(getReviewerEntity(feedback.getReviewer()))
			.formFeedbackEntityList(getFormFeedbackEntityList(feedback))
			.createdAt(feedback.getCreatedAt())
			.updatedAt(feedback.getUpdatedAt())
			.isRead(feedback.isRead())
			.build();
	}

	private static ReviewerEntity getReviewerEntity(Reviewer reviewer) {
		return ReviewerEntity.builder()
			.id(reviewer.getId())
			.nickName(reviewer.getNickName())
			.position(reviewer.getPosition())
			.collaborationExperience(reviewer.isCollaborationExperience())
			.build();
	}

	private static List<FormFeedbackEntity> getFormFeedbackEntityList(Feedback feedback) {
		return feedback.getFormQuestionFeedbackableList().stream()
			.map(f -> {
				if(f instanceof ShortFormQuestionFeedback) {
					return getShortFormFeedbackEntity((ShortFormQuestionFeedback)f);
				}
				return getChoiceFormFeedbackEntity((ChoiceFormQuestionFeedback)f);
			}).collect(Collectors.toList());
	}

	private static ShortFormFeedbackEntity getShortFormFeedbackEntity(
		ShortFormQuestionFeedback shortFormQuestionFeedback) {
		return ShortFormFeedbackEntity.builder()
			.id(shortFormQuestionFeedback.getId())
			.questionId(shortFormQuestionFeedback.getQuestionId())
			.isRead(shortFormQuestionFeedback.isRead())
			.replyList(shortFormQuestionFeedback.getReplyList())
			.build();
	}

	private static ChoiceFormFeedbackEntity getChoiceFormFeedbackEntity(
		ChoiceFormQuestionFeedback choiceFormQuestionFeedback) {
		return ChoiceFormFeedbackEntity.builder()
			.id(choiceFormQuestionFeedback.getId())
			.questionId(choiceFormQuestionFeedback.getQuestionId())
			.isRead(choiceFormQuestionFeedback.isRead())
			.selectSet(choiceFormQuestionFeedback.getSelectedChoiceIdSet())
			.build();
	}

}
