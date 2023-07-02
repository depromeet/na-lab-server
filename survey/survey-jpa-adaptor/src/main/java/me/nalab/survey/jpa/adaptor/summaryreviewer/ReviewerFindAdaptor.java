package me.nalab.survey.jpa.adaptor.summaryreviewer;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import me.nalab.core.data.feedback.ReviewerEntity;
import me.nalab.survey.application.port.out.persistence.summaryreviewer.ReviewerFindPort;
import me.nalab.survey.domain.feedback.Reviewer;
import me.nalab.survey.jpa.adaptor.common.mapper.ReviewerEntityMapper;
import me.nalab.survey.jpa.adaptor.summaryreviewer.repository.ReviewerFindJpaRepository;

@Repository
@RequiredArgsConstructor
public class ReviewerFindAdaptor implements ReviewerFindPort {

	private final ReviewerFindJpaRepository reviewerFindJpaRepository;

	@Override
	public List<Reviewer> findAllReviewer(Long surveyId) {
		List<ReviewerEntity> reviewerEntityList = reviewerFindJpaRepository.findAllReviewerBySurveyId(surveyId);
		return reviewerEntityList.stream().map(ReviewerEntityMapper::toDomain).collect(Collectors.toList());
	}

}
