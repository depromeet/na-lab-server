package me.nalab.survey.application.service.feedbackreviewers;

import static me.nalab.survey.application.RandomFeedbackDtoFixture.getRandomFeedbackDtoBySurvey;
import static me.nalab.survey.application.RandomSurveyDtoFixture.createRandomSurveyDto;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import me.nalab.survey.application.common.feedback.dto.FeedbackDto;
import me.nalab.survey.application.common.feedback.mapper.FeedbackDtoMapper;
import me.nalab.survey.application.common.survey.dto.SurveyDto;
import me.nalab.survey.application.common.survey.mapper.SurveyDtoMapper;
import me.nalab.survey.application.port.out.persistence.feedbackreviewers.FeedbacksFindPort;
import me.nalab.survey.domain.feedback.Feedback;
import me.nalab.survey.domain.survey.Survey;

class FeedbackReviewersFindServiceTest {

	@Mock
	private FeedbacksFindPort feedbacksFindPort;

	private FeedbackReviewersFindService feedbackReviewersFindService;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
		feedbackReviewersFindService = new FeedbackReviewersFindService(feedbacksFindPort);
	}

	@Test
	void FEEDBACKREVIEWERS_FIND_WITH_NO_FEEDBACK() {

		Long surveyId = 1L;
		when(feedbacksFindPort.findAllFeedback(surveyId)).thenReturn(List.of());

		List<Feedback> actualFeedbacks = feedbackReviewersFindService.findAllFeedback(surveyId);

		assertEquals(0, actualFeedbacks.size());
	}

	@Test
	void FEEDBACKREVIEWERS_FIND_WITH_FEEDBACKS() {

		SurveyDto randomSurveyDto = createRandomSurveyDto();
		Long surveyId = randomSurveyDto.getId();
		Survey survey = SurveyDtoMapper.toSurvey(randomSurveyDto);
		FeedbackDto feedbackDto1 = getRandomFeedbackDtoBySurvey(survey);
		FeedbackDto feedbackDto2 = getRandomFeedbackDtoBySurvey(survey);
		Feedback feedback1 = FeedbackDtoMapper.toDomain(survey, feedbackDto1);
		Feedback feedback2 = FeedbackDtoMapper.toDomain(survey, feedbackDto2);
		List<Feedback> feedbacks = List.of(feedback1, feedback2);

		when(feedbacksFindPort.findAllFeedback(surveyId)).thenReturn(feedbacks);

		List<Feedback> resultFeedbacks = feedbackReviewersFindService.findAllFeedback(surveyId);

		assertEquals(feedbacks, resultFeedbacks);
	}

}
