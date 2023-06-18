package me.nalab.survey.jpa.adaptor.findfeedback;

import java.util.List;
import java.util.stream.Collectors;


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
	public void updateFeedback(List<Feedback> feedbackList) {

		List<FeedbackEntity> feedbackEntityList = feedbackList.stream()
			.map(FeedbackEntityMapper::toEntity)
			.collect(Collectors.toList());

		feedbackUpdateRepository.saveAll(feedbackEntityList);
	}
}
