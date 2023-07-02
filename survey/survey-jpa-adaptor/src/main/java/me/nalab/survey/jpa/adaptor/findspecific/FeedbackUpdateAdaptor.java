package me.nalab.survey.jpa.adaptor.findspecific;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import me.nalab.core.data.feedback.FeedbackEntity;
import me.nalab.survey.application.port.out.persistence.findspecific.FeedbackUpdatePort;
import me.nalab.survey.domain.feedback.Feedback;
import me.nalab.survey.jpa.adaptor.common.mapper.FeedbackEntityMapper;
import me.nalab.survey.jpa.adaptor.findspecific.repository.FeedbackUpdateJpaRepository;

@Repository("findspecific.FeedbackUpdateAdaptor")
public class FeedbackUpdateAdaptor implements FeedbackUpdatePort {

	private final FeedbackUpdateJpaRepository feedbackUpdateJpaRepository;

	@Autowired
	FeedbackUpdateAdaptor(
		@Qualifier("findspecific.FeedbackUpdateJpaRepository") FeedbackUpdateJpaRepository feedbackUpdateJpaRepository) {
		this.feedbackUpdateJpaRepository = feedbackUpdateJpaRepository;
	}


	@Override
	public void updateFeedback(Feedback feedback) {
		FeedbackEntity feedbackEntity = FeedbackEntityMapper.toEntity(feedback);
		feedbackUpdateJpaRepository.save(feedbackEntity);
	}
}
