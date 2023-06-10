package me.nalab.survey.jpa.adaptor.findspecific;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import me.nalab.core.data.feedback.FeedbackEntity;
import me.nalab.survey.application.exception.FeedbackDoesNotExistException;
import me.nalab.survey.application.port.out.persistence.findspecific.FeedbackUpdatePort;
import me.nalab.survey.jpa.adaptor.findspecific.repository.FeedbackFindJpaRepository;

@Repository("findspecific.FeedbackUpdateAdaptor")
@RequiredArgsConstructor
public class FeedbackUpdateAdaptor implements FeedbackUpdatePort {

	private final FeedbackFindJpaRepository feedbackFindJpaRepository;
	@Override
	public void updateFeedbackIsReadByFeedbackId(Long feedbackId) {
		FeedbackEntity feedbackEntity = feedbackFindJpaRepository.findById(feedbackId)
			.orElseThrow(() -> new FeedbackDoesNotExistException(feedbackId));
		feedbackEntity.setRead(true);
	}
}
