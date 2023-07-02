package me.nalab.survey.application.service.findfeedbacksummary;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import me.nalab.survey.application.exception.SurveyDoesNotExistException;
import me.nalab.survey.application.port.out.persistence.feedbacksummary.SurveyExistCheckPort;
import me.nalab.survey.application.port.out.persistence.feedbacksummary.TotalFeedbackCountPort;
import me.nalab.survey.application.port.out.persistence.feedbacksummary.UpdatedFeedbackCountPort;

class FeedbackSummaryFindServiceTest {

	@Mock
	private TotalFeedbackCountPort totalFeedbackCountPort;

	@Mock
	private UpdatedFeedbackCountPort updatedFeedbackCountPort;

	@Mock
	private SurveyExistCheckPort surveyExistCheckPort;

	private FeedbackSummaryFindService feedbackSummaryFindService;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
		feedbackSummaryFindService = new FeedbackSummaryFindService(totalFeedbackCountPort, updatedFeedbackCountPort, surveyExistCheckPort);
	}

	@Test
	void FEEDBACK_SUMMARY_FIND_SERVICE_SUCCESS_TEST() {

		Long surveyId = 1L;
		long expectedCount = 10L;
		when(surveyExistCheckPort.isExistSurveyBySurveyId(surveyId)).thenReturn(true);
		when(totalFeedbackCountPort.getTotalFeedbackCountBySurveyId(surveyId)).thenReturn(expectedCount);

		long actualCount = feedbackSummaryFindService.getTotalFeedbackCount(surveyId);

		assertEquals(expectedCount, actualCount);
		verify(totalFeedbackCountPort).getTotalFeedbackCountBySurveyId(surveyId);
	}

	@Test
	void FEEDBACK_SUMMARY_FIND_SERVICE_FAIL_TEST() {

		Long surveyId = 1L;

		when(surveyExistCheckPort.isExistSurveyBySurveyId(surveyId)).thenReturn(false);

		assertThrows(SurveyDoesNotExistException.class, () -> feedbackSummaryFindService.getTotalFeedbackCount(surveyId));
	}

}
