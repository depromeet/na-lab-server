package me.nalab.survey.application.service.findfeedback.formtype;

import static me.nalab.survey.application.RandomSurveyDtoFixture.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import me.nalab.survey.application.RandomFeedbackDtoFixture;
import me.nalab.survey.application.RandomSurveyDtoFixture;
import me.nalab.survey.application.common.feedback.dto.FeedbackDto;
import me.nalab.survey.application.common.feedback.mapper.FeedbackDtoMapper;
import me.nalab.survey.application.common.survey.dto.FormQuestionDtoable;
import me.nalab.survey.application.common.survey.dto.SurveyDto;
import me.nalab.survey.application.common.survey.mapper.SurveyDtoMapper;
import me.nalab.survey.application.exception.SurveyDoesNotExistException;
import me.nalab.survey.application.exception.SurveyDoesNotHasTargetException;
import me.nalab.survey.application.port.out.persistence.findfeedback.formtype.FeedbackFindPort;
import me.nalab.survey.application.port.out.persistence.findfeedback.formtype.SurveyFindPort;
import me.nalab.survey.application.port.out.persistence.findfeedback.formtype.TargetIdFindPort;
import me.nalab.survey.domain.feedback.Feedback;
import me.nalab.survey.domain.survey.ChoiceFormQuestion;
import me.nalab.survey.domain.survey.ShortFormQuestion;
import me.nalab.survey.domain.survey.Survey;

@ExtendWith(SpringExtension.class)
class FeedbackFindByTypeServiceTest {

	private FeedbackFindByTypeService feedbackFindByTypeService;

	@Mock
	private SurveyFindPort surveyFindPort;

	@Mock
	private FeedbackFindPort feedbackFindPort;

	@Mock
	private TargetIdFindPort targetIdFindPort;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		feedbackFindByTypeService = new FeedbackFindByTypeService(surveyFindPort, feedbackFindPort, targetIdFindPort);
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

		List<FeedbackDto> feedbackDtoList = feedbackFindByTypeService.findFeedbackBySurveyId(surveyId);

		assertEquals(List.of(feedbackDto1, feedbackDto2), feedbackDtoList);

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

	@ParameterizedTest
	@ValueSource(strings = {"tendency", "strength", "custom"})
	@DisplayName("formType에 맞는 FormQuestionDtoable 찾기")
	void FIND_ALL_FORMQUESTIONDTOABLE_WITH_FORM_TYPE(String formType) {

		SurveyDto surveyDto = createRandomSurveyDto();
		Survey survey = SurveyDtoMapper.toSurvey(surveyDto);
		Long surveyId = surveyDto.getId();
		Long targetId = surveyDto.getTargetId();
		AtomicInteger expectectedCount = new AtomicInteger(0);

		survey.getFormQuestionableList()
			.forEach(q -> {
				if (q instanceof ChoiceFormQuestion && ((ChoiceFormQuestion)q).getChoiceFormQuestionType()
					.toString()
					.toLowerCase()
					.equals(
						formType))
					expectectedCount.addAndGet(1);
				if (q instanceof ShortFormQuestion && ((ShortFormQuestion)q).getShortFormQuestionType()
					.toString()
					.toLowerCase()
					.equals(
						formType))
					expectectedCount.getAndIncrement();
			});
		when(surveyFindPort.findSurveyById(surveyId)).thenReturn(Optional.of(survey));
		when(targetIdFindPort.findTargetIdBySurveyId(surveyId)).thenReturn(Optional.of(targetId));

		List<FormQuestionDtoable> formQuestionDtoableList = feedbackFindByTypeService.formQuestionMatchingWithType(
			surveyDto.getId(), formType);

		assertEquals(expectectedCount.get(), formQuestionDtoableList.size());

	}

	@Test
	@DisplayName("formType에 맞는 FormQuestionDtoable 찾기 - survey가 존재하지 않을 때")
	void FIND_ALL_FORMQUESTIONDTOABLE_FAIL_WHEN_SURVEY_IS_NOT_EXIST() {

		SurveyDto surveyDto = createRandomSurveyDto();
		Survey survey = SurveyDtoMapper.toSurvey(surveyDto);
		Long surveyId = surveyDto.getId();
		Long targetId = surveyDto.getTargetId();
		String formType = "custom";
		AtomicInteger expectectedCount = new AtomicInteger(0);

		survey.getFormQuestionableList()
			.forEach(q -> {
				if (q instanceof ChoiceFormQuestion && ((ChoiceFormQuestion)q).getChoiceFormQuestionType()
					.toString()
					.toLowerCase()
					.equals(
						formType))
					expectectedCount.addAndGet(1);
				if (q instanceof ShortFormQuestion && ((ShortFormQuestion)q).getShortFormQuestionType()
					.toString()
					.toLowerCase()
					.equals(
						formType))
					expectectedCount.getAndIncrement();
			});
		when(surveyFindPort.findSurveyById(surveyId)).thenReturn(Optional.empty());
		when(targetIdFindPort.findTargetIdBySurveyId(surveyId)).thenReturn(Optional.of(targetId));

		assertThrows(SurveyDoesNotExistException.class,
			() -> feedbackFindByTypeService.formQuestionMatchingWithType(surveyId, "custom"));
	}

	@Test
	@DisplayName("formType에 맞는 FormQuestionDtoable 찾기 - target이 존재하지 않을 때")
	void FIND_ALL_FORMQUESTIONDTOABLE_FAIL_WHEN_TARGET_IS_NOT_EXIST() {

		SurveyDto surveyDto = createRandomSurveyDto();
		Survey survey = SurveyDtoMapper.toSurvey(surveyDto);
		Long surveyId = surveyDto.getId();
		String formType = "custom";
		AtomicInteger expectectedCount = new AtomicInteger(0);

		survey.getFormQuestionableList()
			.forEach(q -> {
				if (q instanceof ChoiceFormQuestion && ((ChoiceFormQuestion)q).getChoiceFormQuestionType()
					.toString()
					.toLowerCase()
					.equals(
						formType))
					expectectedCount.addAndGet(1);
				if (q instanceof ShortFormQuestion && ((ShortFormQuestion)q).getShortFormQuestionType()
					.toString()
					.toLowerCase()
					.equals(
						formType))
					expectectedCount.getAndIncrement();
			});
		when(surveyFindPort.findSurveyById(surveyId)).thenReturn(Optional.of(survey));
		when(targetIdFindPort.findTargetIdBySurveyId(surveyId)).thenReturn(Optional.empty());

		assertThrows(SurveyDoesNotHasTargetException.class,
			() -> feedbackFindByTypeService.formQuestionMatchingWithType(surveyId, "custom"));
	}

}
