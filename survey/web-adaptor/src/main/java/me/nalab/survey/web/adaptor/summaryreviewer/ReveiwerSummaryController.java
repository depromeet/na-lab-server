package me.nalab.survey.web.adaptor.summaryreviewer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import me.nalab.survey.application.port.in.web.summaryreviewer.ReviewerSummaryGetUseCase;
import me.nalab.survey.web.adaptor.summaryreviewer.response.ReviewerSummaryResponse;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class ReveiwerSummaryController {

	private final ReviewerSummaryGetUseCase reviewerSummaryGetUseCase;

	@GetMapping("/reviewers/summary")
	@ResponseStatus(HttpStatus.OK)
	public ReviewerSummaryResponse getReviewerSummary(@RequestAttribute("logined") Long surveyId) {

		ReviewerSummaryGetUseCase.ReviewerSummaryDto reviewerSummaryDto = reviewerSummaryGetUseCase
			.getReviewerSummary(surveyId);

		return ReviewerSummaryResponse.builder()
			.collaborationSummaryResponse(
				ReviewerSummaryResponseMapper.getCollaborationSummaryResponse(reviewerSummaryDto))
			.positionSummaryReponse(ReviewerSummaryResponseMapper.getPositionSummaryResponse(reviewerSummaryDto))
			.build();
	}

}
