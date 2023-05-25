package me.nalab.survey.application.service.findfeedbacksummary;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import me.nalab.survey.application.port.out.persistence.feedbacksummary.TotalFeedbackCountPort;
import me.nalab.survey.application.port.out.persistence.feedbacksummary.UpdatedFeedbackCountPort;

class FeedbackSummaryFindServiceTest {

	@Mock
	private TotalFeedbackCountPort totalFeedbackCountPort;

	@Mock
	private UpdatedFeedbackCountPort updatedFeedbackCountPort;

	private FeedbackSummaryFindService feedbackSummaryFindService;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
		feedbackSummaryFindService = new FeedbackSummaryFindService(totalFeedbackCountPort, updatedFeedbackCountPort);
	}

	@Test
	void FEEDBACK_SUMMARY_FIND_SERVICE_SUCCESS_TEST() {

		Long surveyId = 1L;
		long expectedCount = 10L;
		when(totalFeedbackCountPort.getTotalFeedbackCountBySurveyId(surveyId)).thenReturn(expectedCount);

		long actualCount = feedbackSummaryFindService.getTotalFeedbackCount(surveyId);

		assertEquals(expectedCount, actualCount);
		verify(totalFeedbackCountPort).getTotalFeedbackCountBySurveyId(surveyId);
	}

	@Test
	void FEEDBACK_SUMMARY_FIND_SERVICE_FAIL_TEST() {

		Long surveyId = 1L;
		long expectedCount = 5L;
		when(updatedFeedbackCountPort.getUpdatedFeedbackCountBySurveyId(surveyId)).thenReturn(expectedCount);

		long actualCount = feedbackSummaryFindService.getUpdatedFeedbackCount(surveyId);

		assertEquals(expectedCount, actualCount);
		verify(updatedFeedbackCountPort).getUpdatedFeedbackCountBySurveyId(surveyId);
	}

}
