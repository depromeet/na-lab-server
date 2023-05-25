package me.nalab.survey.jpa.adaptor.feedbackreviewers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import me.nalab.core.data.feedback.FeedbackEntity;
import me.nalab.survey.application.common.feedback.dto.FeedbackDto;
import me.nalab.survey.application.common.feedback.mapper.FeedbackDtoMapper;
import me.nalab.survey.application.port.out.persistence.feedbackreviewers.FeedbacksFindPort;
import me.nalab.survey.domain.feedback.Feedback;
import me.nalab.survey.jpa.adaptor.common.mapper.FeedbackEntityMapper;
import me.nalab.survey.jpa.adaptor.feedbackreviewers.repository.FeedbackFindJpaRepository;

@Repository
@RequiredArgsConstructor
public class FeedbacksReviewersAdaptor implements FeedbacksFindPort {

	private final FeedbackFindJpaRepository feedbackReviewerFindJpaRepository;

	@Override
	public List<FeedbackDto> findAllFeedback(Long surveyId) {
		List<FeedbackEntity> feedbackEntities = feedbackReviewerFindJpaRepository.findBySurveyId(surveyId);
		List<Feedback> feedbacks = feedbackEntities.stream()
			.map(FeedbackEntityMapper::toDomain)
			.collect(Collectors.toList());
		return feedbacks.stream()
			.map(FeedbackDtoMapper::toDto)
			.collect(Collectors.toList());
	}
}
