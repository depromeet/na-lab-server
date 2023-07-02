package me.nalab.survey.web.adaptor.feedbacksummary;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import me.nalab.core.authorization.aop.meta.Auth;
import me.nalab.core.authorization.aop.meta.Authorization;
import me.nalab.survey.application.port.in.web.feedbacksummary.FeedbackSummaryFindUseCase;
import me.nalab.survey.application.service.authorization.SurveyIdValidatorFactory;
import me.nalab.survey.web.adaptor.feedbacksummary.response.FeedbackSummaryResponse;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class FeedbackSummaryController {

	private final FeedbackSummaryFindUseCase feedbackSummaryFindUseCase;

	@Authorization(factory = SurveyIdValidatorFactory.class)
	@GetMapping("/feedbacks/summary")
	public ResponseEntity<FeedbackSummaryResponse> getFeedbackSummary(@Auth @RequestParam("survey-id") Long surveyId) {
		long totalFeedbackCount = feedbackSummaryFindUseCase.getTotalFeedbackCount(surveyId);
		long updatedFeedbackCount = feedbackSummaryFindUseCase.getUpdatedFeedbackCount(surveyId);
		FeedbackSummaryResponse feedbackSummaryResponse = new FeedbackSummaryResponse((int)totalFeedbackCount,
			(int)updatedFeedbackCount);
		return ResponseEntity.ok(feedbackSummaryResponse);
	}
}
