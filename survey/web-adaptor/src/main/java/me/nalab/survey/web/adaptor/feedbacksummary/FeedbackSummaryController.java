package me.nalab.survey.web.adaptor.feedbacksummary;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import me.nalab.survey.application.port.in.web.feedbacksummary.FeedbackSummaryFindUseCase;
import me.nalab.survey.web.adaptor.feedbacksummary.response.FeedbackSummaryResponse;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class FeedbackSummaryController {

	private final FeedbackSummaryFindUseCase feedbackSummaryFindUseCase;

	@GetMapping("/feedbacks/summary")
	public ResponseEntity<FeedbackSummaryResponse> getFeedbackSummary(@RequestParam("survey-id") Long surveyId) {
		int totalFeedbackCount = feedbackSummaryFindUseCase.getTotalFeedbackCount(surveyId);
		int updatedFeedbackCount = feedbackSummaryFindUseCase.getUpdatedFeedbackCount(surveyId);
		FeedbackSummaryResponse feedbackSummaryResponse = new FeedbackSummaryResponse(totalFeedbackCount, updatedFeedbackCount);
		return ResponseEntity.ok(feedbackSummaryResponse);
	}
}
