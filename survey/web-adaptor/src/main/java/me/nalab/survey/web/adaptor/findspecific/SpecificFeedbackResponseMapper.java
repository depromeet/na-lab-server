package me.nalab.survey.web.adaptor.findspecific;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import me.nalab.survey.application.common.feedback.dto.ChoiceFormQuestionFeedbackDto;
import me.nalab.survey.application.common.feedback.dto.FeedbackDto;
import me.nalab.survey.application.common.feedback.dto.FormQuestionFeedbackDtoable;
import me.nalab.survey.application.common.feedback.dto.ShortFormQuestionFeedbackDto;
import me.nalab.survey.application.common.survey.dto.ChoiceFormQuestionDto;
import me.nalab.survey.application.common.survey.dto.ShortFormQuestionDto;
import me.nalab.survey.application.common.survey.dto.SurveyDto;
import me.nalab.survey.web.adaptor.findspecific.response.ChoiceFormFeedbackResponse;
import me.nalab.survey.web.adaptor.findspecific.response.ChoiceResponse;
import me.nalab.survey.web.adaptor.findspecific.response.FormFeedbackResponseable;
import me.nalab.survey.web.adaptor.findspecific.response.ReviewerResponse;
import me.nalab.survey.web.adaptor.findspecific.response.ShortFormFeedbackResponse;
import me.nalab.survey.web.adaptor.findspecific.response.SpecificFeedbackResponse;

class SpecificFeedbackResponseMapper {

	private SpecificFeedbackResponseMapper() {
		throw new UnsupportedOperationException("Cannot invoke constructor \"SpecificFeedbackResponseMapper()\"");
	}

	static SpecificFeedbackResponse toSpecificFeedbackResponse(SurveyDto surveyDto, FeedbackDto feedbackDto) {
		return SpecificFeedbackResponse.builder()
			.feedbackId(feedbackDto.getId())
			.createdAt(ZonedDateTime.ofInstant(feedbackDto.getCreatedAt(), ZoneId.of("Asia/Seoul")).toLocalDateTime())
			.reviewer(ReviewerResponse.builder()
				.nickName(feedbackDto.getReviewerDto().getNickName())
				.collaborationExperience(feedbackDto.getReviewerDto().isCollaborationExperience())
				.position(feedbackDto.getReviewerDto().getPosition())
				.build())
			.question(findQuestionFeedbackResponses(surveyDto, feedbackDto))
			.build();
	}

	private static List<FormFeedbackResponseable> findQuestionFeedbackResponses(SurveyDto surveyDto,
		FeedbackDto feedbackDto) {
		return surveyDto.getFormQuestionDtoableList().stream()
			.map(i -> {
				if(i instanceof ShortFormQuestionDto) {
					return toShortFormFeedbackResponse((ShortFormQuestionDto)i,
						(ShortFormQuestionFeedbackDto)findFormQuestionFeedbackableByQuestionId(
							feedbackDto.getFormQuestionFeedbackDtoableList(), i.getId()));
				}
				return toChoiceFormFeedbackResponse((ChoiceFormQuestionDto)i,
					(ChoiceFormQuestionFeedbackDto)findFormQuestionFeedbackableByQuestionId(
						feedbackDto.getFormQuestionFeedbackDtoableList(), i.getId()));
			})
			.collect(Collectors.toList());
	}

	private static FormQuestionFeedbackDtoable findFormQuestionFeedbackableByQuestionId(
		List<FormQuestionFeedbackDtoable> formQuestionFeedbackDtoableList, Long questionId) {
		Optional<FormQuestionFeedbackDtoable> formQuestionFeedbackDtoable = formQuestionFeedbackDtoableList.stream()
			.filter(it -> it.getQuestionId().equals(questionId))
			.findAny();
		if(formQuestionFeedbackDtoable.isEmpty()) {
			throw new IllegalStateException();
		}
		return formQuestionFeedbackDtoable.get();
	}

	private static ShortFormFeedbackResponse toShortFormFeedbackResponse(ShortFormQuestionDto questionDto,
		ShortFormQuestionFeedbackDto feedbackDto) {
		return ShortFormFeedbackResponse.builder()
			.questionId(questionDto.getId())
			.type("short")
			.formType(questionDto.getShortFormQuestionDtoType().name().toLowerCase())
			.title(questionDto.getTitle())
			.order(questionDto.getOrder())
			.isRead(feedbackDto.isRead())
			.reply(feedbackDto.getReplyList())
			.build();
	}

	private static ChoiceFormFeedbackResponse toChoiceFormFeedbackResponse(ChoiceFormQuestionDto questionDto,
		ChoiceFormQuestionFeedbackDto feedbackDto) {
		List<ChoiceResponse> choices = questionDto.getChoiceDtoList().stream()
			.filter(it -> feedbackDto.getSelectedChoiceIdSet().contains(it.getId()))
			.map(it -> ChoiceResponse.builder()
				.choiceId(it.getId())
				.content(it.getContent())
				.order(it.getOrder())
				.build())
			.collect(Collectors.toList());

		return ChoiceFormFeedbackResponse.builder()
			.questionId(questionDto.getId())
			.type("choice")
			.formType(questionDto.getChoiceFormQuestionDtoType().name().toLowerCase())
			.title(questionDto.getTitle())
			.order(questionDto.getOrder())
			.isRead(feedbackDto.isRead())
			.choices(choices)
			.build();
	}

}
