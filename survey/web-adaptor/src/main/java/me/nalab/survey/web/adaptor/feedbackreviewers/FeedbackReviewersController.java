package me.nalab.survey.web.adaptor.feedbackreviewers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import me.nalab.core.authorization.aop.meta.Auth;
import me.nalab.core.authorization.aop.meta.Authorization;
import me.nalab.survey.application.common.feedback.dto.FeedbackDto;
import me.nalab.survey.application.service.authorization.SurveyIdValidatorFactory;
import me.nalab.survey.application.service.feedbackreviewers.FeedbackReviewersFindService;
import me.nalab.survey.web.adaptor.feedbackreviewers.response.FeedbackReviewersResponse;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class FeedbackReviewersController {

	private final FeedbackReviewersFindService feedbackReviewersFindService;

	@Authorization(factory = SurveyIdValidatorFactory.class)
	@GetMapping("/reviewers")
	public ResponseEntity<FeedbackReviewersResponse> getFeedbackReviewers(
		@Auth @RequestParam("survey-id") Long surveyId) {
		List<FeedbackDto> feedbacks = feedbackReviewersFindService.findAllFeedback(surveyId);
		FeedbackReviewersResponse feedbackReviewersResponse = FeedbackReviewersResponseMapper.toFeedbackReviewersResponse(
			feedbacks);
		return ResponseEntity.ok(feedbackReviewersResponse);
	}
}
