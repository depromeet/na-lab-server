package me.nalab.survey.web.adaptor.summaryreviewer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import me.nalab.core.authorization.aop.meta.Auth;
import me.nalab.core.authorization.aop.meta.Authorization;
import me.nalab.survey.application.port.in.web.summaryreviewer.ReviewerSummarizeUseCase;
import me.nalab.survey.application.service.authorization.SurveyIdValidatorFactory;
import me.nalab.survey.web.adaptor.summaryreviewer.response.ReviewerSummaryResponse;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class ReveiwerSummaryController {

	private final ReviewerSummarizeUseCase reviewerSummarizeUseCase;

	@Authorization(factory = SurveyIdValidatorFactory.class)
	@GetMapping("/reviewers/summary")
	@ResponseStatus(HttpStatus.OK)
	public ReviewerSummaryResponse getReviewerSummary(@Auth @RequestParam("survey-id") Long surveyId) {

		ReviewerSummarizeUseCase.ReviewerSummaryDto reviewerSummaryDto = reviewerSummarizeUseCase
			.summarizeReviewerBySurveyId(surveyId);

		return ReviewerSummaryResponse.builder()
			.collaborationSummaryResponse(
				ReviewerSummaryResponseMapper.getCollaborationSummaryResponse(reviewerSummaryDto))
			.positionSummaryReponse(ReviewerSummaryResponseMapper.getPositionSummaryResponse(reviewerSummaryDto))
			.build();
	}

}
