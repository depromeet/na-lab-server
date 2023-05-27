package me.nalab.survey.web.adaptor.findspecific;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import me.nalab.survey.application.common.feedback.dto.ChoiceFormQuestionFeedbackDto;
import me.nalab.survey.application.common.feedback.dto.FeedbackDto;
import me.nalab.survey.application.common.feedback.dto.FormQuestionFeedbackDtoable;
import me.nalab.survey.application.common.feedback.dto.ShortFormQuestionFeedbackDto;
import me.nalab.survey.application.common.survey.dto.ChoiceFormQuestionDto;
import me.nalab.survey.application.common.survey.dto.ShortFormQuestionDto;
import me.nalab.survey.application.common.survey.dto.SurveyDto;
import me.nalab.survey.application.port.in.web.findspecific.SpecificFindUseCase;
import me.nalab.survey.web.adaptor.findspecific.response.ChoiceFormFeedbackResponse;
import me.nalab.survey.web.adaptor.findspecific.response.ChoiceResponse;
import me.nalab.survey.web.adaptor.findspecific.response.FormFeedbackResponseable;
import me.nalab.survey.web.adaptor.findspecific.response.ReviewerResponse;
import me.nalab.survey.web.adaptor.findspecific.response.ShortFormFeedbackResponse;
import me.nalab.survey.web.adaptor.findspecific.response.SpecificFeedbackResponse;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class SpecificFeedbackController {

	private final SpecificFindUseCase specificFindUseCase;

	@GetMapping("/feedbacks/{feedbackId}")
	public ResponseEntity<SpecificFeedbackResponse> getSpecificFeedback(@PathVariable Long feedbackId) {
		FeedbackDto feedbackDto = specificFindUseCase.findFeedbackByFeedbackId(feedbackId);
		SurveyDto surveyDto = specificFindUseCase.findSurveyBySurveyId(feedbackDto.getSurveyId());
		SpecificFeedbackResponse specificFeedbackResponse = toSpecificFeedbackResponse(surveyDto, feedbackDto);
		return ResponseEntity.ok(specificFeedbackResponse);
	}

	private SpecificFeedbackResponse toSpecificFeedbackResponse(SurveyDto surveyDto, FeedbackDto feedbackDto) {
		return SpecificFeedbackResponse.builder()
			.feedbackId(feedbackDto.getId())
			.createdAt(feedbackDto.getCreatedAt())
			.reviewer(ReviewerResponse.builder()
				.nickName(feedbackDto.getReviewerDto().getNickName())
				.collaborationExperience(feedbackDto.getReviewerDto().isCollaborationExperience())
				.position(feedbackDto.getReviewerDto().getPosition())
				.build())
			.question(getQuestionFeedbackResponses(surveyDto, feedbackDto))
			.build();
	}

	private List<FormFeedbackResponseable> getQuestionFeedbackResponses(SurveyDto surveyDto,
		FeedbackDto feedbackDto) {
		return surveyDto.getFormQuestionDtoableList().stream()
			.map(i -> {
				if (i instanceof ShortFormQuestionDto) {
					return toShortFormFeedbackResponse((ShortFormQuestionDto)i, (ShortFormQuestionFeedbackDto)findFormQuestionFeedbackableByQuestionId(
						feedbackDto.getFormQuestionFeedbackDtoableList(), i.getId()));
				}
				return toChoiceFormFeedbackResponse((ChoiceFormQuestionDto)i, (ChoiceFormQuestionFeedbackDto)findFormQuestionFeedbackableByQuestionId(
					feedbackDto.getFormQuestionFeedbackDtoableList(), i.getId()));
			})
			.collect(Collectors.toList());
	}

	private FormQuestionFeedbackDtoable findFormQuestionFeedbackableByQuestionId(
		List<FormQuestionFeedbackDtoable> formQuestionFeedbackDtoableList, Long questionId) {
		Optional<FormQuestionFeedbackDtoable> formQuestionFeedbackDtoable = formQuestionFeedbackDtoableList.stream()
			.filter(it -> it.getQuestionId().equals(questionId))
			.findFirst();
		if (formQuestionFeedbackDtoable.isEmpty()) {
			throw new IllegalStateException();
		}
		return formQuestionFeedbackDtoable.get();
	}

	private ShortFormFeedbackResponse toShortFormFeedbackResponse(ShortFormQuestionDto questionDto, ShortFormQuestionFeedbackDto feedbackDto) {
		return ShortFormFeedbackResponse.builder()
			.questionId(questionDto.getId())
			.type("short")
			.title(questionDto.getTitle())
			.order(questionDto.getOrder())
			.isRead(feedbackDto.isRead())
			.reply(feedbackDto.getReplyList())
			.build();
	}

	private ChoiceFormFeedbackResponse toChoiceFormFeedbackResponse(ChoiceFormQuestionDto questionDto, ChoiceFormQuestionFeedbackDto feedbackDto) {
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
			.title(questionDto.getTitle())
			.order(questionDto.getOrder())
			.isRead(feedbackDto.isRead())
			.choices(choices)
			.build();
	}

}
