package me.nalab.survey.application.service.findfeedback;

import static me.nalab.survey.application.RandomFeedbackDtoFixture.getRandomFeedbackDtoBySurvey;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import me.nalab.survey.application.RandomSurveyDtoFixture;
import me.nalab.survey.application.common.feedback.dto.FeedbackDto;
import me.nalab.survey.application.common.feedback.mapper.FeedbackDtoMapper;
import me.nalab.survey.application.common.survey.mapper.SurveyDtoMapper;
import me.nalab.survey.application.exception.SurveyDoesNotExistException;
import me.nalab.survey.application.port.in.web.findfeedback.FeedbackFindUseCase;
import me.nalab.survey.application.port.out.persistence.findfeedback.FeedbackFindPort;
import me.nalab.survey.application.port.out.persistence.findfeedback.SurveyExistCheckPort;
import me.nalab.survey.domain.feedback.Feedback;
import me.nalab.survey.domain.survey.Survey;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = FeedbackFindService.class)
class FeedbackFindServiceTest {

	@Autowired
	private FeedbackFindUseCase feedbackFindUseCase;

	@MockBean
	private FeedbackFindPort feedbackFindPort;

	@MockBean(name = "findfeedback.SurveyExistCheckAdaptor")
	private SurveyExistCheckPort surveyExistCheckPort;

	@ParameterizedTest
	@MethodSource("feedbackFindSources")
	@DisplayName("Feedback 조회 성공 테스트 - survey가 존재")
	void FIND_FEEDBACK(Survey survey, List<FeedbackDto> feedbackDtoList) {
		// given
		List<Feedback> feedbackList = feedbackDtoList.stream()
			.map(f -> FeedbackDtoMapper.toDomain(survey, f))
			.collect(Collectors.toList());

		// when
		when(surveyExistCheckPort.isExistSurveyBySurveyId(anyLong())).thenReturn(true);
		when(feedbackFindPort.findAllFeedbackBySurveyId(anyLong())).thenReturn(feedbackList);

		List<FeedbackDto> result = feedbackFindUseCase.findAllFeedbackDtoBySurveyId(survey.getId());

		// then
		assertIsSorted(result);
	}

	@Test
	@DisplayName("Feedback 조회 실패 테스트 - survey가 없을때")
	void FIND_FEEDBACK_FAIL_NO_SURVEY() {
		// when
		when(surveyExistCheckPort.isExistSurveyBySurveyId(anyLong())).thenReturn(false);

		// then
		assertThrows(SurveyDoesNotExistException.class, () -> feedbackFindUseCase.findAllFeedbackDtoBySurveyId(1L));
	}

	private void assertIsSorted(List<FeedbackDto> result) {
		FeedbackDto before = null;
		for(FeedbackDto current : result) {
			if(before == null) {
				before = current;
				continue;
			}
			assertTrue(before.getUpdatedAt().isAfter(current.getUpdatedAt()));
		}
	}

	private static Stream<Arguments> feedbackFindSources() {
		Survey survey = SurveyDtoMapper.toSurvey(RandomSurveyDtoFixture.createRandomSurveyDto());
		return Stream.of(
			Arguments.of(survey,
				Arrays.asList(getRandomFeedbackDtoBySurvey(survey), getRandomFeedbackDtoBySurvey(survey),
					getRandomFeedbackDtoBySurvey(survey))), // feedback이 여러개
			Arguments.of(survey, Collections.singletonList(getRandomFeedbackDtoBySurvey(survey))), // feedback이 하나
			Arguments.of(survey, List.of()) // feedback이 없을때
		);
	}

}
