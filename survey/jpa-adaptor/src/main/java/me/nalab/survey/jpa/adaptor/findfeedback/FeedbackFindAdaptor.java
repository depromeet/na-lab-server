package me.nalab.survey.jpa.adaptor.findfeedback;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import me.nalab.core.data.feedback.FeedbackEntity;
import me.nalab.survey.application.port.out.persistence.findfeedback.FeedbackFindPort;
import me.nalab.survey.domain.feedback.Feedback;
import me.nalab.survey.jpa.adaptor.common.mapper.FeedbackEntityMapper;
import me.nalab.survey.jpa.adaptor.findfeedback.repository.FeedbackFindRepository;

@Repository
@RequiredArgsConstructor
public class FeedbackFindAdaptor implements FeedbackFindPort {

	private final FeedbackFindRepository feedbackFindRepository;

	@Override
	public List<Feedback> findAllFeedbackBySurveyId(Long surveyId) {
		List<FeedbackEntity> feedbackEntityList = feedbackFindRepository.findAllFeedbackEntityBySurveyId(surveyId);
		return feedbackEntityList.stream().map(FeedbackEntityMapper::toDomain).collect(Collectors.toList());
	}

}
