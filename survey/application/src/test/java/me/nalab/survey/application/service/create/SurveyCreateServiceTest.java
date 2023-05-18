package me.nalab.survey.application.service.create;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import me.nalab.survey.application.RandomSurveyDtoFixture;
import me.nalab.survey.application.TestIdGenerator;
import me.nalab.survey.application.common.dto.SurveyDto;
import me.nalab.survey.application.exception.TargetDoesNotExistException;
import me.nalab.survey.application.port.in.web.CreateSurveyUseCase;
import me.nalab.survey.application.port.out.persistence.SurveyCreatePort;
import me.nalab.survey.application.port.out.persistence.TargetExistCheckPort;
import me.nalab.survey.domain.exception.IdAlreadyGeneratedException;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SurveyCreateService.class, TestIdGenerator.class})
class SurveyCreateServiceTest {

	@Autowired
	private CreateSurveyUseCase createSurveyUseCase;

	@MockBean
	private TestIdGenerator idGenerator;

	@MockBean
	private SurveyCreatePort createSurveyPort;

	@MockBean
	private TargetExistCheckPort findTargetPort;

	@ParameterizedTest
	@MethodSource("surveyDtoLargeNullIdSources")
	void CREATE_NEW_SURVEY_SUCCESS(SurveyDto surveyDto) {
		// given
		Long targetId = 1L;
		idGenerator.setIdGenerateAlgorithm(() -> 1L);

		// when
		when(findTargetPort.isExistTarget(targetId)).thenReturn(true);

		// then
		assertDoesNotThrow(() -> createSurveyUseCase.createSurvey(targetId, surveyDto));
	}

	@ParameterizedTest
	@MethodSource("surveyDtoSmallNotNullIdSources")
	void CREATE_NEW_SURVEY_FAIL_DUPLICATED_ID_CALL(SurveyDto surveyDto) {
		// given
		Long targetId = 1L;
		idGenerator.setIdGenerateAlgorithm(() -> 1L);

		// when
		when(findTargetPort.isExistTarget(targetId)).thenReturn(true);

		// then
		assertThrows(IdAlreadyGeneratedException.class, () -> createSurveyUseCase.createSurvey(targetId, surveyDto));
	}

	@Test
	@DisplayName("Survey 생성 실패 테스트 - TargetId를 찾을 수 없음")
	void CREATE_NEW_SURVEY_FAIL_CANNOT_FIND_TARGET_ID() {
		// given
		Long targetId = 1L;

		// when
		when(findTargetPort.isExistTarget(targetId)).thenReturn(false);

		// then
		assertThrows(TargetDoesNotExistException.class, () -> createSurveyUseCase.createSurvey(targetId, null));
	}

	private static Stream<SurveyDto> surveyDtoLargeNullIdSources() {
		RandomSurveyDtoFixture.initGenerator();
		RandomSurveyDtoFixture.setRandomIdGenerator(() -> null);
		List<SurveyDto> surveyDtoList = new ArrayList<>();
		for(int i = 0; i < 100; i++) {
			surveyDtoList.add(RandomSurveyDtoFixture.createRandomSurveyDto());
		}
		return surveyDtoList.stream();
	}

	private static Stream<SurveyDto> surveyDtoSmallNotNullIdSources() {
		RandomSurveyDtoFixture.initGenerator();
		RandomSurveyDtoFixture.setRandomIdGenerator(() -> 1L);
		List<SurveyDto> surveyDtoList = new ArrayList<>();
		for(int i = 0; i < 20; i++) {
			surveyDtoList.add(RandomSurveyDtoFixture.createRandomSurveyDto());
		}
		return surveyDtoList.stream();
	}

}
