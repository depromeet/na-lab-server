package me.nalab.survey.application.service.findfeedbacksummary;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import me.nalab.survey.application.port.in.web.feedbacksummary.FeedbackSummaryFindUseCase;
import me.nalab.survey.application.port.out.persistence.feedbacksummary.TotalFeedbackCountPort;
import me.nalab.survey.application.port.out.persistence.feedbacksummary.UpdatedFeedbackCountPort;

@Service
@RequiredArgsConstructor
public class FeedbackSummaryFindService implements FeedbackSummaryFindUseCase {

	private final TotalFeedbackCountPort totalFeedbackCountPort;
	private final UpdatedFeedbackCountPort updatedFeedbackCountPort;

	@Override
	public int getTotalFeedbackCount(Long surveyId) {
		return totalFeedbackCountPort.getTotalFeedbackCountBySurveyId(surveyId);
	}

	@Override
	public int getUpdatedFeedbackCount(Long surveyId) {
		return updatedFeedbackCountPort.getUpdatedFeedbackCountBySurveyId(surveyId);
	}
}
