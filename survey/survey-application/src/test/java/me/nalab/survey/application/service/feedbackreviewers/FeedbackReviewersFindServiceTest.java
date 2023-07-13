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
import me.nalab.survey.application.exception.SurveyDoesNotExistException;
import me.nalab.survey.application.port.out.persistence.feedbackreviewers.FeedbacksFindPort;
import me.nalab.survey.application.port.out.persistence.feedbackreviewers.SurveyExistCheckPort;
import me.nalab.survey.domain.feedback.Feedback;
import me.nalab.survey.domain.survey.Survey;

class FeedbackReviewersFindServiceTest {

	@Mock
	private FeedbacksFindPort feedbacksFindPort;

	@Mock
	private SurveyExistCheckPort surveyExistCheckPort;

	private FeedbackReviewersFindService feedbackReviewersFindService;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
		feedbackReviewersFindService = new FeedbackReviewersFindService(feedbacksFindPort, surveyExistCheckPort);
	}

	@Test
	void FEEDBACKREVIEWERS_FIND_WITH_NO_FEEDBACK() {

		Long surveyId = 1L;
		when(surveyExistCheckPort.isExistSurveyBySurveyId(surveyId)).thenReturn(false);
		when(feedbacksFindPort.findAllFeedback(surveyId)).thenReturn(List.of());

		assertThrows(SurveyDoesNotExistException.class, () -> feedbackReviewersFindService.findAllFeedback(surveyId));
	}

	@Test
	void FEEDBACKREVIEWERS_FIND_WITH_FEEDBACKS() {

		SurveyDto randomSurveyDto = createRandomSurveyDto();
		Long surveyId = randomSurveyDto.getId();
		Survey survey = SurveyDtoMapper.toSurvey(randomSurveyDto);
		Feedback feedback1 = FeedbackDtoMapper.toDomain(survey, getRandomFeedbackDtoBySurvey(survey));
		Feedback feedback2 = FeedbackDtoMapper.toDomain(survey, getRandomFeedbackDtoBySurvey(survey));
		List<Feedback> feedbacks = List.of(feedback1, feedback2);
		List<FeedbackDto> expectedFeedbacks = List.of(
			FeedbackDtoMapper.toDto(feedback1),
			FeedbackDtoMapper.toDto(feedback2)
		);

		when(surveyExistCheckPort.isExistSurveyBySurveyId(surveyId)).thenReturn(true);
		when(feedbacksFindPort.findAllFeedback(surveyId)).thenReturn(feedbacks);

		List<FeedbackDto> resultFeedbacks = feedbackReviewersFindService.findAllFeedback(surveyId);

		assertEquals(expectedFeedbacks, resultFeedbacks);
	}

}
