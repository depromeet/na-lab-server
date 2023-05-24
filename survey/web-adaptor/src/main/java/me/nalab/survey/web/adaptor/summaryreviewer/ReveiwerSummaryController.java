package me.nalab.survey.web.adaptor.summaryreviewer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import me.nalab.survey.application.port.in.web.summaryreviewer.ReviewerSummaryGetUseCase;
import me.nalab.survey.web.adaptor.summaryreviewer.response.CollaborationSummaryResponse;
import me.nalab.survey.web.adaptor.summaryreviewer.response.PositionSummaryReponse;
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
			.collaborationSummaryResponse(getCollaborationSummaryResponse(reviewerSummaryDto))
			.positionSummaryReponse(getPositionSummaryResponse(reviewerSummaryDto))
			.build();
	}

	private CollaborationSummaryResponse getCollaborationSummaryResponse(
		ReviewerSummaryGetUseCase.ReviewerSummaryDto reviewerSummaryDto) {

		return CollaborationSummaryResponse.builder()
			.yes(reviewerSummaryDto.getCollaborationExperience().getYes())
			.no(reviewerSummaryDto.getCollaborationExperience().getNo())
			.build();
	}

	private PositionSummaryReponse getPositionSummaryResponse(
		ReviewerSummaryGetUseCase.ReviewerSummaryDto reviewerSummaryDto) {

		return PositionSummaryReponse.builder()
			.designer(reviewerSummaryDto.getPosition().getPositionReplyMap().getOrDefault("designer", 0))
			.productManager(reviewerSummaryDto.getPosition().getPositionReplyMap().getOrDefault("product-manager", 0))
			.programmer(reviewerSummaryDto.getPosition().getPositionReplyMap().getOrDefault("programmer", 0))
			.other(reviewerSummaryDto.getPosition().getPositionReplyMap().getOrDefault("other", 0))
			.build();
	}

}
