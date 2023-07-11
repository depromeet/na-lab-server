package me.nalab.survey.application.service.findfeedback.type;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import me.nalab.survey.application.RandomFeedbackDtoFixture;
import me.nalab.survey.application.RandomSurveyDtoFixture;
import me.nalab.survey.application.common.feedback.dto.FeedbackDto;
import me.nalab.survey.application.common.feedback.mapper.FeedbackDtoMapper;
import me.nalab.survey.application.common.survey.dto.FormQuestionDtoable;
import me.nalab.survey.application.common.survey.mapper.SurveyDtoMapper;
import me.nalab.survey.application.exception.SurveyDoesNotHasFormTypeException;
import me.nalab.survey.application.port.out.persistence.findfeedback.type.FeedbackFindPort;
import me.nalab.survey.application.port.out.persistence.findfeedback.type.FormQuestionFindPort;
import me.nalab.survey.domain.feedback.Feedback;
import me.nalab.survey.domain.survey.FormQuestionable;
import me.nalab.survey.domain.survey.Survey;

@ExtendWith(SpringExtension.class)
class FeedbackFindByTypeServiceTest {

	private FeedbackFindByTypeService feedbackFindByTypeService;

	@Mock
	private FormQuestionFindPort formQuestionFindPort;

	@Mock
	private FeedbackFindPort feedbackFindPort;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		feedbackFindByTypeService = new FeedbackFindByTypeService(formQuestionFindPort, feedbackFindPort);
	}

	@Test
	@DisplayName("surveyId와 type으로 formQuestion 찾기 - 성공테스트")
	void FIND_FORM_QUESTION_BY_SURVEY_ID_AND_TYPE_SUCCESS_TEST() {

		Survey survey = SurveyDtoMapper.toSurvey(RandomSurveyDtoFixture.createRandomSurveyDto());
		FormQuestionable formQuestionable = survey.getFormQuestionableList().get(0);
		FormQuestionDtoable formQuestionDtoable = SurveyDtoMapper.getFormQuestionDtoable(formQuestionable);
		String questionType = survey.getFormQuestionableList().get(0).getQuestionType().toString();

		when(formQuestionFindPort.findFormQuestionableBySurveyIdAndType(survey.getId(), questionType))
			.thenReturn(List.of(formQuestionable));

		List<FormQuestionDtoable> formQuestionDtoableList = feedbackFindByTypeService.findFormQuestionBySurveyIdAndType(
			survey.getId(), questionType);

		assertTrue(formQuestionDtoableList.contains(formQuestionDtoable));
	}

	@Test
	@DisplayName("surveyId와 type으로 formQuestion 찾기 - 실패테스트")
	void FIND_FORM_QUESTION_BY_SURVEY_ID_AND_TYPE_FAILED_TEST() {

		Survey survey = SurveyDtoMapper.toSurvey(RandomSurveyDtoFixture.createRandomSurveyDto());
		Long surveyId = survey.getId();
		String questionType = survey.getFormQuestionableList().get(0).getQuestionType().toString();

		when(formQuestionFindPort.findFormQuestionableBySurveyIdAndType(survey.getId(), questionType.toString()))
			.thenReturn(List.of());

		assertThrows(SurveyDoesNotHasFormTypeException.class,
			() -> feedbackFindByTypeService.findFormQuestionBySurveyIdAndType(surveyId, questionType));

	}

	@Test
	@DisplayName("surveyId로 모든 feedback 찾기 - 피드백이 있는 경우")
	void FIND_ALL_FEEDBACK_BY_SURVEY_ID_WITH_FEEDBACK() {

		Survey survey = SurveyDtoMapper.toSurvey(RandomSurveyDtoFixture.createRandomSurveyDto());
		Long surveyId = survey.getId();
		FeedbackDto feedbackDto1 = RandomFeedbackDtoFixture.getRandomFeedbackDtoBySurvey(survey);
		FeedbackDto feedbackDto2 = RandomFeedbackDtoFixture.getRandomFeedbackDtoBySurvey(survey);
		Feedback feedback1 = FeedbackDtoMapper.toDomain(survey, feedbackDto1);
		Feedback feedback2 = FeedbackDtoMapper.toDomain(survey, feedbackDto2);

		when(feedbackFindPort.findFeedbackBySurveyId(surveyId)).thenReturn(List.of(feedback1, feedback2));

		List<FeedbackDto> feedbackDtoBySurveyId = feedbackFindByTypeService.findFeedbackBySurveyId(surveyId);

		assertEquals(List.of(feedbackDto1, feedbackDto2), feedbackDtoBySurveyId);

	}

	@Test
	@DisplayName("surveyId로 모든 feedback 찾기 - 피드백이 없는 경우")
	void FIND_ALL_FEEDBACK_BY_SURVEY_ID_WITH_NO_FEEDBACK() {

		Survey survey = SurveyDtoMapper.toSurvey(RandomSurveyDtoFixture.createRandomSurveyDto());
		Long surveyId = survey.getId();
		when(feedbackFindPort.findFeedbackBySurveyId(surveyId)).thenReturn(List.of());

		List<FeedbackDto> feedbackDtoBySurveyId = feedbackFindByTypeService.findFeedbackBySurveyId(surveyId);

		assertEquals(List.of(), feedbackDtoBySurveyId);

	}

}
