package me.nalab.survey.jpa.adaptor.createfeedback;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import me.nalab.core.data.feedback.FeedbackEntity;
import me.nalab.survey.application.port.out.persistence.createfeedback.FeedbackSavePort;
import me.nalab.survey.domain.feedback.Feedback;
import me.nalab.survey.jpa.adaptor.common.mapper.FeedbackEntityMapper;
import me.nalab.survey.jpa.adaptor.createfeedback.repository.FeedbackCreateJpaRepository;

@Repository
@RequiredArgsConstructor
public class FeedbackSaveAdaptor implements FeedbackSavePort {

	private final FeedbackCreateJpaRepository feedbackCreateJpaRepository;

	@Override
	public void saveFeedback(Feedback feedback) {
		FeedbackEntity feedbackEntity = FeedbackEntityMapper.toEntity(feedback);
		feedbackCreateJpaRepository.save(feedbackEntity);
	}

}
