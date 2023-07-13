package me.nalab.survey.jpa.adaptor.findfeedback.formtype;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import me.nalab.core.data.feedback.FeedbackEntity;
import me.nalab.survey.application.port.out.persistence.findfeedback.formtype.FeedbackFindPort;
import me.nalab.survey.domain.feedback.Feedback;
import me.nalab.survey.jpa.adaptor.common.mapper.FeedbackEntityMapper;
import me.nalab.survey.jpa.adaptor.findfeedback.formtype.repository.FeedbackFindRepository;

@Repository("findfeedback.formtype.FeedbackFindAdaptor")
public class FeedbackFindAdaptor implements FeedbackFindPort {

	private final FeedbackFindRepository feedbackFindRepository;

	@Autowired
	FeedbackFindAdaptor(
		@Qualifier("findfeedback.formtype.FeedbackFindRepository") FeedbackFindRepository feedbackFindRepository) {
		this.feedbackFindRepository = feedbackFindRepository;
	}

	@Override
	public List<Feedback> findFeedbackBySurveyId(Long surveyId) {
		List<FeedbackEntity> feedbackEntityList = feedbackFindRepository.findAllFeedbackEntityBySurveyId(surveyId);
		return feedbackEntityList.stream()
			.map(FeedbackEntityMapper::toDomain)
			.collect(Collectors.toList());
	}

}
