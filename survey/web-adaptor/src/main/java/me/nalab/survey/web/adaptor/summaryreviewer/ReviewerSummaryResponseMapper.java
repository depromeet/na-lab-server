package me.nalab.survey.web.adaptor.summaryreviewer;

import me.nalab.survey.application.port.in.web.summaryreviewer.ReviewerSummarizeUseCase;
import me.nalab.survey.web.adaptor.summaryreviewer.response.CollaborationSummaryResponse;
import me.nalab.survey.web.adaptor.summaryreviewer.response.PositionSummaryReponse;

final class ReviewerSummaryResponseMapper {

	private ReviewerSummaryResponseMapper() {
		throw new UnsupportedOperationException("Cannot invoke constructor \"ReviewerSummaryResponseMapper()\"");
	}

	static CollaborationSummaryResponse getCollaborationSummaryResponse(
		ReviewerSummarizeUseCase.ReviewerSummaryDto reviewerSummaryDto) {

		return CollaborationSummaryResponse.builder()
			.yes(reviewerSummaryDto.getCollaborationExperience().getYes())
			.no(reviewerSummaryDto.getCollaborationExperience().getNo())
			.build();
	}

	static PositionSummaryReponse getPositionSummaryResponse(
		ReviewerSummarizeUseCase.ReviewerSummaryDto reviewerSummaryDto) {

		return PositionSummaryReponse.builder()
			.designer(reviewerSummaryDto.getPosition().getPositionReplyMap().getOrDefault("designer", 0))
			.productManager(reviewerSummaryDto.getPosition().getPositionReplyMap().getOrDefault("pm", 0))
			.developer(reviewerSummaryDto.getPosition().getPositionReplyMap().getOrDefault("developer", 0))
			.others(reviewerSummaryDto.getPosition().getPositionReplyMap().getOrDefault("others", 0))
			.build();
	}

}
