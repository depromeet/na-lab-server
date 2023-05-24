package me.nalab.survey.jpa.adaptor.findfeedbacksummary;

import java.util.List;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import me.nalab.core.data.feedback.FeedbackEntity;
import me.nalab.survey.application.port.out.persistence.feedbacksummary.TotalFeedbackCountPort;
import me.nalab.survey.application.port.out.persistence.feedbacksummary.UpdatedFeedbackCountPort;
import me.nalab.survey.jpa.adaptor.findfeedbacksummary.repository.FeedbackCountJpaRepository;

@Repository
@RequiredArgsConstructor
public class FeedbackCountAdaptor implements TotalFeedbackCountPort, UpdatedFeedbackCountPort {

	private final FeedbackCountJpaRepository feedbackCountJpaRepository;

	@Override
	public int getTotalFeedbackCountBySurveyId(Long surveyId) {
		List<FeedbackEntity> feedbackEntities = feedbackCountJpaRepository.findBySurveyId(surveyId);
		return feedbackEntities.size();
	}

	@Override
	public int getUpdatedFeedbackCountBySurveyId(Long surveyId) {
		List<FeedbackEntity> feedbackEntities = feedbackCountJpaRepository.findBySurveyIdAndIsRead(surveyId, false);
		return feedbackEntities.size();
	}
}
