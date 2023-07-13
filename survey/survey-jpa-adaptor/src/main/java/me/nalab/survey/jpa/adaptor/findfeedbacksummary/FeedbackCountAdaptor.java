package me.nalab.survey.jpa.adaptor.findfeedbacksummary;


import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import me.nalab.survey.application.port.out.persistence.feedbacksummary.TotalFeedbackCountPort;
import me.nalab.survey.application.port.out.persistence.feedbacksummary.UpdatedFeedbackCountPort;
import me.nalab.survey.jpa.adaptor.findfeedbacksummary.repository.FeedbackCountJpaRepository;

@Repository
@RequiredArgsConstructor
public class FeedbackCountAdaptor implements TotalFeedbackCountPort, UpdatedFeedbackCountPort {

	private final FeedbackCountJpaRepository feedbackCountJpaRepository;

	@Override
	public long getTotalFeedbackCountBySurveyId(Long surveyId) {
		return feedbackCountJpaRepository.countBySurveyId(surveyId);
	}

	@Override
	public long getUpdatedFeedbackCountBySurveyId(Long surveyId) {
		return feedbackCountJpaRepository.countBySurveyIdAndIsReadFalse(surveyId);
	}
}
