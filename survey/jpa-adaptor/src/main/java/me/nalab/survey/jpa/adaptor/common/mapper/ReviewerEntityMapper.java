package me.nalab.survey.jpa.adaptor.common.mapper;

import me.nalab.core.data.feedback.ReviewerEntity;
import me.nalab.survey.domain.feedback.Reviewer;

public final class ReviewerEntityMapper {

	private ReviewerEntityMapper() {
		throw new UnsupportedOperationException("Cannot invoke constructor \"ReviewerEntityMapper()\"");
	}

	public static Reviewer toDomain(ReviewerEntity reviewerentity) {
		return Reviewer.builder()
			.id(reviewerentity.getId())
			.nickName(reviewerentity.getNickName())
			.position(reviewerentity.getPosition())
			.collaborationExperience(reviewerentity.isCollaborationExperience())
			.build();
	}

}
