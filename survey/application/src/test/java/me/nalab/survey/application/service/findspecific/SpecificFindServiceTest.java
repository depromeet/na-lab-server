package me.nalab.survey.application.service.findspecific;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import me.nalab.survey.application.RandomFeedbackDtoFixture;
import me.nalab.survey.application.RandomSurveyDtoFixture;
import me.nalab.survey.application.common.feedback.dto.FeedbackDto;
import me.nalab.survey.application.common.feedback.mapper.FeedbackDtoMapper;
import me.nalab.survey.application.common.survey.dto.SurveyDto;
import me.nalab.survey.application.common.survey.mapper.SurveyDtoMapper;
import me.nalab.survey.application.exception.FeedbackDoesNotExistException;
import me.nalab.survey.application.exception.SurveyDoesNotExistException;
import me.nalab.survey.application.exception.SurveyDoesNotHasTargetException;
import me.nalab.survey.application.port.out.persistence.findspecific.FeedbackFindPort;
import me.nalab.survey.application.port.out.persistence.findspecific.SurveyFindPort;
import me.nalab.survey.domain.feedback.Feedback;
import me.nalab.survey.domain.survey.Survey;

class SpecificFindServiceTest {

	private SpecificFindService specificFindService;

	@Mock
	private FeedbackFindPort feedbackFindPort;

	@Mock
	private SurveyFindPort surveyFindPort;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		specificFindService = new SpecificFindService(feedbackFindPort, surveyFindPort);
	}

	@Test
	void FIND_FEEDBACK_BY_FEEDBACKID_WITH_EXISTING_FEEDBACK_ID() {
		SurveyDto surveyDto = RandomSurveyDtoFixture.createRandomSurveyDto();
		Survey survey = SurveyDtoMapper.toSurvey(surveyDto);
		FeedbackDto feedbackDto = RandomFeedbackDtoFixture.getRandomFeedbackDtoBySurvey(survey);
		Feedback feedback = FeedbackDtoMapper.toDomain(survey, feedbackDto);
		Long feedbackId = feedback.getId();
		when(feedbackFindPort.findFeedback(feedbackId)).thenReturn(Optional.of(feedback));

		FeedbackDto resultFeedbackDto = specificFindService.findFeedbackByFeedbackId(feedbackId);

		assertAll(
			() -> assertEquals(feedbackDto.getId(), resultFeedbackDto.getId()),
			() -> assertEquals(feedbackDto.getSurveyId(), resultFeedbackDto.getSurveyId()),
			() -> assertEquals(feedbackDto.getReviewerDto(), resultFeedbackDto.getReviewerDto()),
			() -> assertEquals(feedbackDto.getFormQuestionFeedbackDtoableList(),
				resultFeedbackDto.getFormQuestionFeedbackDtoableList())
		);
	}

	@Test
	void FIND_FEEDBACK_BY_FEEDBACKID_WITH_NON_EXISTING_FEEDBACK_ID() {

		Long feedbackId = 1L;
		when(feedbackFindPort.findFeedback(feedbackId)).thenReturn(Optional.empty());

		assertThrows(FeedbackDoesNotExistException.class,
			() -> specificFindService.findFeedbackByFeedbackId(feedbackId));
	}

	@Test
	void FIND_SURVEY_WITH_EXISTING_SURVEY_ID() {
		SurveyDto surveyDto = RandomSurveyDtoFixture.createRandomSurveyDto();
		Survey survey = SurveyDtoMapper.toSurvey(surveyDto);
		Long targetId = surveyDto.getTargetId();
		Long surveyId = survey.getId();
		when(surveyFindPort.findSurvey(surveyId)).thenReturn(Optional.of(survey));
		when(surveyFindPort.findTargetIdBySurveyId(surveyId)).thenReturn(Optional.of(targetId));

		SurveyDto resultSurveyDto = specificFindService.findSurveyBySurveyId(surveyId);

		assertEquals(surveyId, resultSurveyDto.getId());
		assertEquals(targetId, resultSurveyDto.getTargetId());
	}

	@Test
	void FIND_SURVEY_BY_SURVEY_ID_WITH_NON_EXISTING_SURVEY_ID() {
		Long surveyId = 1L;

		when(surveyFindPort.findSurvey(surveyId)).thenReturn(Optional.empty());

		assertThrows(SurveyDoesNotExistException.class,
			() -> specificFindService.findSurveyBySurveyId(surveyId));
	}

	@Test
	void FIND_SURVEY_BY_SURVEY_ID_WITH_NON_EXISTING_TARGET_ID() {
		Long surveyId = 1L;
		SurveyDto surveyDto = RandomSurveyDtoFixture.createRandomSurveyDto();
		Survey survey = SurveyDtoMapper.toSurvey(surveyDto);

		when(surveyFindPort.findSurvey(surveyId)).thenReturn(Optional.of(survey));
		when(surveyFindPort.findTargetIdBySurveyId(surveyId)).thenReturn(Optional.empty());

		assertThrows(SurveyDoesNotHasTargetException.class,
			() -> specificFindService.findSurveyBySurveyId(surveyId));
	}
}
