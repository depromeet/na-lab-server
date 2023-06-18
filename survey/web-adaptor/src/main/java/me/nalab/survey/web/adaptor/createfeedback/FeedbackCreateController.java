package me.nalab.survey.web.adaptor.createfeedback;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import me.nalab.core.secure.xss.meta.Xss;
import me.nalab.core.secure.xss.meta.XssFiltering;
import me.nalab.survey.application.common.feedback.dto.ChoiceFormQuestionFeedbackDto;
import me.nalab.survey.application.common.feedback.dto.FeedbackDto;
import me.nalab.survey.application.common.feedback.dto.FormQuestionFeedbackDtoable;
import me.nalab.survey.application.common.feedback.dto.ReviewerDto;
import me.nalab.survey.application.common.feedback.dto.ShortFormQuestionFeedbackDto;
import me.nalab.survey.application.port.in.web.createfeedback.FeedbackCreateUseCase;
import me.nalab.survey.web.adaptor.createfeedback.request.AbstractQuestionFeedbackRequest;
import me.nalab.survey.web.adaptor.createfeedback.request.ChoiceQuestionFeedbackRequest;
import me.nalab.survey.web.adaptor.createfeedback.request.FeedbackCreateRequest;
import me.nalab.survey.web.adaptor.createfeedback.request.ReviewerRequest;
import me.nalab.survey.web.adaptor.createfeedback.request.ShortQuestionFeedbackRequest;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class FeedbackCreateController {

	private final FeedbackCreateUseCase feedbackCreateUseCase;

	@XssFiltering
	@PostMapping("/feedbacks")
	@ResponseStatus(HttpStatus.CREATED)
	public void createFeedback(@RequestParam("survey-id") Long surveyId,
		@Xss("json") @Validated @RequestBody FeedbackCreateRequest feedbackCreateRequest) {
		feedbackCreateUseCase.createFeedback(surveyId, toFeedbackDto(feedbackCreateRequest));
	}

	private FeedbackDto toFeedbackDto(FeedbackCreateRequest feedbackCreateRequest) {
		return FeedbackDto.builder()
			.reviewerDto(toReviewerDto(feedbackCreateRequest.getReviewerRequest()))
			.formQuestionFeedbackDtoableList(
				toFormQuestionFeedbackDtoableList(feedbackCreateRequest.getAbstractQuestionFeedbackRequestList())
			)
			.build();
	}

	private ReviewerDto toReviewerDto(ReviewerRequest reviewerRequest) {
		return ReviewerDto.builder()
			.position(reviewerRequest.getPosition())
			.collaborationExperience(reviewerRequest.isCollaborationExperience())
			.build();
	}

	private List<FormQuestionFeedbackDtoable> toFormQuestionFeedbackDtoableList(
		List<AbstractQuestionFeedbackRequest> abstractQuestionFeedbackRequestList) {
		return abstractQuestionFeedbackRequestList.stream()
			.map(r -> {
				if(r instanceof ChoiceQuestionFeedbackRequest) {
					return toChoiceFormQuestionFeedbackDto((ChoiceQuestionFeedbackRequest)r);
				}
				return toShortFormQuestionFeedbackDto((ShortQuestionFeedbackRequest)r);
			})
			.collect(Collectors.toList());
	}

	private ChoiceFormQuestionFeedbackDto toChoiceFormQuestionFeedbackDto(
		ChoiceQuestionFeedbackRequest choiceQuestionFeedbackRequest) {
		return ChoiceFormQuestionFeedbackDto.builder()
			.questionId(Long.valueOf(choiceQuestionFeedbackRequest.getQuestionId()))
			.selectedChoiceIdSet(choiceQuestionFeedbackRequest.getChoiceSet().stream().map(Long::valueOf).collect(
				Collectors.toSet()))
			.build();
	}

	private ShortFormQuestionFeedbackDto toShortFormQuestionFeedbackDto(
		ShortQuestionFeedbackRequest shortQuestionFeedbackRequest) {
		return ShortFormQuestionFeedbackDto.builder()
			.questionId(Long.valueOf(shortQuestionFeedbackRequest.getQuestionId()))
			.replyList(shortQuestionFeedbackRequest.getReplyList())
			.build();
	}

}
