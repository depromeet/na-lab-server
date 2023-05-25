package me.nalab.survey.web.adaptor.summaryreviewer;

import me.nalab.survey.application.port.in.web.summaryreviewer.ReviewerSummaryGetUseCase;
import me.nalab.survey.web.adaptor.summaryreviewer.response.CollaborationSummaryResponse;
import me.nalab.survey.web.adaptor.summaryreviewer.response.PositionSummaryReponse;

final class ReviewerSummaryResponseMapper {

	private ReviewerSummaryResponseMapper(){
		throw new UnsupportedOperationException("Cannot invoke constructor \"ReviewerSummaryResponseMapper()\"");
	}

	static CollaborationSummaryResponse getCollaborationSummaryResponse(
		ReviewerSummaryGetUseCase.ReviewerSummaryDto reviewerSummaryDto) {

		return CollaborationSummaryResponse.builder()
			.yes(reviewerSummaryDto.getCollaborationExperience().getYes())
			.no(reviewerSummaryDto.getCollaborationExperience().getNo())
			.build();
	}

	static PositionSummaryReponse getPositionSummaryResponse(
		ReviewerSummaryGetUseCase.ReviewerSummaryDto reviewerSummaryDto) {

		return PositionSummaryReponse.builder()
			.designer(reviewerSummaryDto.getPosition().getPositionReplyMap().getOrDefault("designer", 0))
			.productManager(reviewerSummaryDto.getPosition().getPositionReplyMap().getOrDefault("product-manager", 0))
			.programmer(reviewerSummaryDto.getPosition().getPositionReplyMap().getOrDefault("programmer", 0))
			.other(reviewerSummaryDto.getPosition().getPositionReplyMap().getOrDefault("other", 0))
			.build();
	}

}
