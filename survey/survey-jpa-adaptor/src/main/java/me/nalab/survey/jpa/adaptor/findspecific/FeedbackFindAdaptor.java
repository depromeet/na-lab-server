package me.nalab.survey.jpa.adaptor.findspecific;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import me.nalab.core.data.feedback.FeedbackEntity;
import me.nalab.survey.application.port.out.persistence.findspecific.FeedbackFindPort;
import me.nalab.survey.domain.feedback.Feedback;
import me.nalab.survey.jpa.adaptor.common.mapper.FeedbackEntityMapper;
import me.nalab.survey.jpa.adaptor.findspecific.repository.FeedbackFindJpaRepository;

@Repository("findspecific.FeedbackFindAdaptor")
@RequiredArgsConstructor
public class FeedbackFindAdaptor implements FeedbackFindPort {

	private final FeedbackFindJpaRepository feedbackFindJpaRepository;

	@Override
	public Optional<Feedback> findFeedback(Long feedbackId) {
		Optional<FeedbackEntity> feedbackEntity = feedbackFindJpaRepository.findById(feedbackId);
		if(feedbackEntity.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(FeedbackEntityMapper.toDomain(feedbackEntity.get()));
	}
}
