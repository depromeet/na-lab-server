package me.nalab.survey.application.service.createfeedback;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import me.nalab.survey.application.RandomFeedbackDtoFixture;
import me.nalab.survey.application.RandomSurveyDtoFixture;
import me.nalab.survey.application.TestIdGenerator;
import me.nalab.survey.application.common.feedback.dto.FeedbackDto;
import me.nalab.survey.application.common.survey.mapper.SurveyDtoMapper;
import me.nalab.survey.application.exception.SurveyDoesNotExistException;
import me.nalab.survey.application.port.in.web.createfeedback.FeedbackCreateUseCase;
import me.nalab.survey.application.port.out.persistence.createfeedback.FeedbackSavePort;
import me.nalab.survey.application.port.out.persistence.createfeedback.ReviewerLatestNameFindPort;
import me.nalab.survey.application.port.out.persistence.createfeedback.SurveyFindPort;
import me.nalab.survey.domain.survey.Survey;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {FeedbackCreateService.class, TestIdGenerator.class})
class FeedbackCreateServiceTest {

	@Autowired
	private FeedbackCreateUseCase feedbackCreateUseCase;

	@Autowired
	private TestIdGenerator testIdGenerator;

	@MockBean
	private FeedbackSavePort feedbackSavePort;

	@MockBean
	private ReviewerLatestNameFindPort reviewerLatestNameFindPort;

	@MockBean
	private SurveyFindPort surveyFindPort;

	@Test
	@DisplayName("피드백 생성 성공 테스트")
	void CREATE_FEEDBACK_SUCCESS() {
		// given
		RandomSurveyDtoFixture.setRandomIdGenerator(() -> testIdGenerator.generate());
		Survey survey = SurveyDtoMapper.toSurvey(RandomSurveyDtoFixture.createRandomSurveyDto());
		FeedbackDto feedbackDto = RandomFeedbackDtoFixture.getRandomFeedbackDtoBySurvey(survey);

		// when
		when(surveyFindPort.findSurveyBySurveyId(survey.getId())).thenReturn(Optional.of(survey));
		when(reviewerLatestNameFindPort.getLatestReviewerNameBySurveyId(survey.getId())).thenReturn(Optional.of("A"));

		// then
		assertDoesNotThrow(() -> feedbackCreateUseCase.createFeedback(survey.getId(), feedbackDto));
	}

	@Test
	@DisplayName("피드백 생성 성공 테스트 - first reviewer")
	void CREATE_FEEDBACK_SUCCESS_FIRST_REVIEWER() {
		// given
		RandomSurveyDtoFixture.setRandomIdGenerator(() -> testIdGenerator.generate());
		Survey survey = SurveyDtoMapper.toSurvey(RandomSurveyDtoFixture.createRandomSurveyDto());
		FeedbackDto feedbackDto = RandomFeedbackDtoFixture.getRandomFeedbackDtoBySurvey(survey);

		// when
		when(surveyFindPort.findSurveyBySurveyId(survey.getId())).thenReturn(Optional.of(survey));
		when(reviewerLatestNameFindPort.getLatestReviewerNameBySurveyId(survey.getId())).thenReturn(Optional.empty());

		// then
		assertDoesNotThrow(() -> feedbackCreateUseCase.createFeedback(survey.getId(), feedbackDto));
	}

	@Test
	@DisplayName("피드백 생성 실패 테스트 - survey를 찾을 수 없음")
	void CREATE_FEEDBACK_FAIL_CANNOT_FIND_SURVEY() {
		// given
		RandomSurveyDtoFixture.setRandomIdGenerator(() -> testIdGenerator.generate());
		Survey survey = SurveyDtoMapper.toSurvey(RandomSurveyDtoFixture.createRandomSurveyDto());
		Long surveyId = survey.getId();
		FeedbackDto feedbackDto = RandomFeedbackDtoFixture.getRandomFeedbackDtoBySurvey(survey);

		// when
		when(surveyFindPort.findSurveyBySurveyId(anyLong())).thenReturn(Optional.empty());
		when(reviewerLatestNameFindPort.getLatestReviewerNameBySurveyId(survey.getId())).thenReturn(Optional.empty());

		// then
		assertThrows(SurveyDoesNotExistException.class,
			() -> feedbackCreateUseCase.createFeedback(surveyId, feedbackDto));
	}

}
