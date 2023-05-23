package me.nalab.survey.jpa.adaptor.findfeedbacksummary;

import java.util.List;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import me.nalab.core.data.feedback.FeedbackEntity;
import me.nalab.survey.application.port.out.persistence.feedbacksummary.TotalFeedbackCountPort;
import me.nalab.survey.jpa.adaptor.findfeedbacksummary.repository.TotalFeedbackCountJpaRepository;

@Repository
@RequiredArgsConstructor
public class TotalFeedbackCountAdaptor implements TotalFeedbackCountPort {

	private final TotalFeedbackCountJpaRepository totalFeedbackCountJpaRepository;

	@Override
	public int getTotalFeedbackCountBySurveyId(Long surveyId) {
		List<FeedbackEntity> feedbackEntities = totalFeedbackCountJpaRepository.findBySurveyId(surveyId);
		return feedbackEntities.size();
	}
}
