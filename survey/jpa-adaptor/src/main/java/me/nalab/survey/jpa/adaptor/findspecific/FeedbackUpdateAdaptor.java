package me.nalab.survey.jpa.adaptor.findspecific;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import me.nalab.core.data.feedback.FeedbackEntity;
import me.nalab.survey.application.port.out.persistence.findspecific.FeedbackUpdatePort;
import me.nalab.survey.jpa.adaptor.findspecific.repository.FeedbackFindJpaRepository;

@Repository("findspecific.FeedbackUpdateAdaptor")
@RequiredArgsConstructor
public class FeedbackUpdateAdaptor implements FeedbackUpdatePort {

	private final FeedbackFindJpaRepository feedbackFindJpaRepository;
	@Override
	public Optional<Long> updateFeedbackIsReadByFeedbackId(Long feedbackId) {
		Optional<FeedbackEntity> optionalFeedbackEntity = feedbackFindJpaRepository.findById(feedbackId);
		if (optionalFeedbackEntity.isEmpty()) return Optional.empty();

		FeedbackEntity feedbackEntity = optionalFeedbackEntity.get();
		feedbackEntity.setRead(true);
		return Optional.of(feedbackEntity.getId());
	}
}
