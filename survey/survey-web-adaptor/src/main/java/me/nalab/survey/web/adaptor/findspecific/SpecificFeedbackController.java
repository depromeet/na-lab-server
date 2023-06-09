package me.nalab.survey.web.adaptor.findspecific;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import me.nalab.core.authorization.aop.meta.Auth;
import me.nalab.core.authorization.aop.meta.Authorization;
import me.nalab.survey.application.common.feedback.dto.FeedbackDto;
import me.nalab.survey.application.common.survey.dto.SurveyDto;
import me.nalab.survey.application.port.in.web.findspecific.SpecificFindUseCase;
import me.nalab.survey.application.service.authorization.FeedbackIdValidatorFactory;
import me.nalab.survey.web.adaptor.findspecific.response.SpecificFeedbackResponse;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class SpecificFeedbackController {

	private final SpecificFindUseCase specificFindUseCase;

	@Authorization(factory = FeedbackIdValidatorFactory.class)
	@GetMapping("/feedbacks/{feedback-id}")
	public ResponseEntity<SpecificFeedbackResponse> getSpecificFeedback(
		@Auth @PathVariable("feedback-id") Long feedbackId) {
		FeedbackDto feedbackDto = specificFindUseCase.findFeedbackByFeedbackId(feedbackId);
		specificFindUseCase.updateFeedbackIsReadByFeedbackId(feedbackId);
		SurveyDto surveyDto = specificFindUseCase.findSurveyBySurveyId(feedbackDto.getSurveyId());
		SpecificFeedbackResponse specificFeedbackResponse = SpecificFeedbackResponseMapper.toSpecificFeedbackResponse(
			surveyDto, feedbackDto);
		return ResponseEntity.ok(specificFeedbackResponse);
	}

}
