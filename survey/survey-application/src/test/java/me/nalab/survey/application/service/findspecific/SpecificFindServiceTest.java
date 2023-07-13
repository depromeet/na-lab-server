package me.nalab.survey.application.service.findspecific;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import me.nalab.survey.application.RandomFeedbackDtoFixture;
import me.nalab.survey.application.RandomSurveyDtoFixture;
import me.nalab.survey.application.TestIdGenerator;
import me.nalab.survey.application.common.feedback.dto.FeedbackDto;
import me.nalab.survey.application.common.feedback.mapper.FeedbackDtoMapper;
import me.nalab.survey.application.common.survey.dto.SurveyDto;
import me.nalab.survey.application.common.survey.mapper.SurveyDtoMapper;
import me.nalab.survey.application.exception.FeedbackDoesNotExistException;
import me.nalab.survey.application.exception.SurveyDoesNotExistException;
import me.nalab.survey.application.exception.SurveyDoesNotHasTargetException;
import me.nalab.survey.application.port.out.persistence.findspecific.FeedbackFindPort;
import me.nalab.survey.application.port.out.persistence.findspecific.SurveyFindPort;
import me.nalab.survey.application.port.out.persistence.findspecific.FeedbackUpdatePort;
import me.nalab.survey.domain.feedback.Feedback;
import me.nalab.survey.domain.survey.Survey;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestIdGenerator.class})
class SpecificFindServiceTest {

	private SpecificFindService specificFindService;

	@Autowired
	private TestIdGenerator testIdGenerator;

	@Mock
	private FeedbackFindPort feedbackFindPort;

	@Mock
	private SurveyFindPort surveyFindPort;

	@Mock
	private FeedbackUpdatePort feedbackUpdatePort;


	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		specificFindService = new SpecificFindService(feedbackFindPort, surveyFindPort, feedbackUpdatePort);
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

	@Test
	void UPDATE_FEEDBACK_IS_READ_BY_FEEDBACK_ID() {

		RandomSurveyDtoFixture.setRandomIdGenerator(() -> testIdGenerator.generate());
		Survey survey = SurveyDtoMapper.toSurvey(RandomSurveyDtoFixture.createRandomSurveyDto());
		FeedbackDto feedbackDto = RandomFeedbackDtoFixture.getRandomFeedbackDtoBySurvey(survey);
		Feedback feedback = FeedbackDtoMapper.toDomain(survey, feedbackDto);

		when(feedbackFindPort.findFeedback(feedback.getId())).thenReturn(Optional.of(feedback));
		specificFindService.updateFeedbackIsReadByFeedbackId(feedback.getId());

		verify(feedbackUpdatePort).updateFeedback(feedback);
		assertTrue(feedback.isRead());
	}

	@Test
	void UPDATE_FEEDBACK_IS_READ_BY_NON_EXISTING_FEEDBACK_ID() {
		Long feedbackId = 1L;

		assertThrows(FeedbackDoesNotExistException.class,
			() -> specificFindService.updateFeedbackIsReadByFeedbackId(feedbackId));
	}
}
