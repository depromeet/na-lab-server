package me.nalab.survey.jpa.adaptor.findfeedback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import me.nalab.core.data.feedback.FeedbackEntity;
import me.nalab.survey.application.port.out.persistence.findfeedback.FeedbackUpdatePort;
import me.nalab.survey.domain.feedback.Feedback;
import me.nalab.survey.jpa.adaptor.common.mapper.FeedbackEntityMapper;
import me.nalab.survey.jpa.adaptor.findfeedback.repository.FeedbackUpdateRepository;

@Repository("findfeedback.FeedbackUpdateAdaptor")
public class FeedbackUpdateAdaptor implements FeedbackUpdatePort {

	private final FeedbackUpdateRepository feedbackUpdateRepository;

	@Autowired
	FeedbackUpdateAdaptor(
		@Qualifier("findfeedback.FeedbackUpdateRepository") FeedbackUpdateRepository feedbackUpdateRepository) {
		this.feedbackUpdateRepository = feedbackUpdateRepository;
	}

	@Override
	public void updateFeedback(Feedback feedback) {
		FeedbackEntity feedbackEntity = FeedbackEntityMapper.toEntity(feedback);
		feedbackUpdateRepository.save(feedbackEntity);
	}
}
