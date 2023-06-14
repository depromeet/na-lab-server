package me.nalab.survey.web.adaptor.findfeedback;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import me.nalab.survey.application.common.feedback.dto.ChoiceFormQuestionFeedbackDto;
import me.nalab.survey.application.common.feedback.dto.FeedbackDto;
import me.nalab.survey.application.common.feedback.dto.ReviewerDto;
import me.nalab.survey.application.common.feedback.dto.ShortFormQuestionFeedbackDto;
import me.nalab.survey.application.common.survey.dto.ChoiceDto;
import me.nalab.survey.application.common.survey.dto.ChoiceFormQuestionDto;
import me.nalab.survey.application.common.survey.dto.QuestionDtoType;
import me.nalab.survey.application.common.survey.dto.ShortFormQuestionDto;
import me.nalab.survey.application.common.survey.dto.SurveyDto;
import me.nalab.survey.web.adaptor.findfeedback.response.QuestionFeedbackResponse;
import me.nalab.survey.web.adaptor.findfeedback.response.feedback.ChoiceFeedbackResponse;
import me.nalab.survey.web.adaptor.findfeedback.response.feedback.ReviewerResponse;
import me.nalab.survey.web.adaptor.findfeedback.response.feedback.ShortFeedbackResponse;
import me.nalab.survey.web.adaptor.findfeedback.response.survey.AbstractSurveyResponse;
import me.nalab.survey.web.adaptor.findfeedback.response.survey.ChoiceSurveyResponse;
import me.nalab.survey.web.adaptor.findfeedback.response.survey.ShortSurveyResponse;

final class ResponseMapper {

	private ResponseMapper() {
		throw new UnsupportedOperationException("Cannot invoke constructor \"ResponseMapper()\"");
	}

	static QuestionFeedbackResponse toQuestionFeedbackResponse(SurveyDto surveyDto, List<FeedbackDto> feedbackDtoList) {
		return QuestionFeedbackResponse.builder()
			.abstractSurveyResponse(surveyDto.getFormQuestionDtoableList().stream().map(
				f -> {
					if(f.getQuestionDtoType() == QuestionDtoType.CHOICE) {
						return toChoiceSurveyResponse((ChoiceFormQuestionDto)f, feedbackDtoList);
					}
					return toShortSurveyResponse((ShortFormQuestionDto)f, feedbackDtoList);
				}
			).collect(Collectors.toList())).build();
	}

	private static AbstractSurveyResponse toChoiceSurveyResponse(ChoiceFormQuestionDto choiceFormQuestionDto,
		List<FeedbackDto> feedbackDto) {
		return ChoiceSurveyResponse.builder()
			.questionId(String.valueOf(choiceFormQuestionDto.getId()))
			.order(choiceFormQuestionDto.getOrder())
			.type(choiceFormQuestionDto.getQuestionDtoType().name().toLowerCase())
			.formType(choiceFormQuestionDto.getChoiceFormQuestionDtoType().name().toLowerCase())
			.title(choiceFormQuestionDto.getTitle())
			.choiceResponseList(toChoiceQuestionResponse(choiceFormQuestionDto.getChoiceDtoList()))
			.choiceFeedbackResponseList(toChoiceFeedbackResponse(choiceFormQuestionDto.getId(), feedbackDto))
			.build();
	}

	private static List<ChoiceSurveyResponse.QuestionResponse> toChoiceQuestionResponse(List<ChoiceDto> choiceDtoList) {
		return choiceDtoList.stream().map(
			c -> ChoiceSurveyResponse.QuestionResponse.builder()
				.choiceId(String.valueOf(c.getId()))
				.order(c.getOrder())
				.content(c.getContent())
				.build()).collect(Collectors.toList()
		);
	}

	private static List<ChoiceFeedbackResponse> toChoiceFeedbackResponse(Long questionId,
		List<FeedbackDto> feedbackDtoList) {
		List<ChoiceFeedbackResponse> choiceFeedbackResponseList = new ArrayList<>();
		feedbackDtoList.forEach(f -> addChoiceFeedbackResponse(choiceFeedbackResponseList, questionId, f));
		return choiceFeedbackResponseList;
	}

	private static void addChoiceFeedbackResponse(List<ChoiceFeedbackResponse> choiceFeedbackResponseList,
		Long questionId, FeedbackDto feedbackDto) {
		feedbackDto.getFormQuestionFeedbackDtoableList().stream()
			.filter(q -> q.getQuestionId().equals(questionId))
			.forEach(cq -> {
				Set<String> selectedChoiceIdSet = ((ChoiceFormQuestionFeedbackDto)cq).getSelectedChoiceIdSet()
					.stream()
					.map(String::valueOf)
					.collect(
						Collectors.toSet());
				choiceFeedbackResponseList.add(
					ChoiceFeedbackResponse.builder()
						.id(String.valueOf(feedbackDto.getId()))
						.choiceIdSet(selectedChoiceIdSet)
						.createdAt(feedbackDto.getCreatedAt())
						.read(feedbackDto.isRead())
						.reviewerResponse(toReviewerResponse(feedbackDto.getReviewerDto()))
						.build()
				);
			});
	}

	private static ReviewerResponse toReviewerResponse(ReviewerDto reviewerDto) {
		return ReviewerResponse.builder()
			.id(String.valueOf(reviewerDto.getId()))
			.nickName(reviewerDto.getNickName())
			.collaborationExperience(reviewerDto.isCollaborationExperience())
			.position(reviewerDto.getPosition())
			.build();
	}

	private static AbstractSurveyResponse toShortSurveyResponse(ShortFormQuestionDto shortFormQuestionDto,
		List<FeedbackDto> feedbackDtoList) {
		return ShortSurveyResponse.builder()
			.questionId(String.valueOf(shortFormQuestionDto.getId()))
			.order(shortFormQuestionDto.getOrder())
			.type(shortFormQuestionDto.getQuestionDtoType().name().toLowerCase())
			.formType(shortFormQuestionDto.getShortFormQuestionDtoType().name().toLowerCase())
			.title(shortFormQuestionDto.getTitle())
			.shortFeedbackResponseList(toShortFeedbackResponse(shortFormQuestionDto.getId(), feedbackDtoList))
			.build();
	}

	private static List<ShortFeedbackResponse> toShortFeedbackResponse(Long questionId,
		List<FeedbackDto> feedbackDtoList) {
		List<ShortFeedbackResponse> choiceFeedbackResponseList = new ArrayList<>();
		feedbackDtoList.forEach(f -> addShortFeedbackResponse(choiceFeedbackResponseList, questionId, f));
		return choiceFeedbackResponseList;
	}

	private static void addShortFeedbackResponse(List<ShortFeedbackResponse> shortFeedbackResponseList, Long questionId,
		FeedbackDto feedbackDto) {
		feedbackDto.getFormQuestionFeedbackDtoableList().stream().filter(q -> q.getQuestionId().equals(questionId))
			.forEach(sq -> shortFeedbackResponseList.add(
				ShortFeedbackResponse.builder()
					.id(String.valueOf(feedbackDto.getId()))
					.replyList(((ShortFormQuestionFeedbackDto)sq).getReplyList())
					.createdAt(feedbackDto.getCreatedAt())
					.read(feedbackDto.isRead())
					.reviewerResponse(toReviewerResponse(feedbackDto.getReviewerDto()))
					.build()
			));
	}

}
