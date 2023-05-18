package me.nalab.survey.application.service.target.find;

import static me.nalab.survey.application.RandomSurveyDtoFixture.createRandomSurveyDto;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import me.nalab.survey.application.common.dto.SurveyDto;
import me.nalab.survey.application.common.mapper.SurveyDtoMapper;
import me.nalab.survey.application.port.out.persistence.survey.find.SurveyFindPort;
import me.nalab.survey.application.port.out.persistence.target.find.TargetFindPort;
import me.nalab.survey.application.service.survey.find.SurveyFindService;
import me.nalab.survey.domain.survey.Survey;

class TargetFindServiceTest {

	@Mock
	private TargetFindPort targetFindPort;

	@Mock
	private SurveyFindPort surveyFindPort;

	private SurveyFindService surveyFindService;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
		surveyFindService = new SurveyFindService(targetFindPort, surveyFindPort);
	}

	@Test
	void TARGET_FIND_SERVICE_TEST() {
		// 총 테스트 돌릴 횟수
		int numberOfTestCases = 10;
		for(int i = 0; i < numberOfTestCases; i++) {
			// ramdom으로 생성
			SurveyDto randomSurveyDto = createRandomSurveyDto();
			Long surveyId = randomSurveyDto.getId();
			Long targetId = randomSurveyDto.getTargetId();
			Survey survey = SurveyDtoMapper.toSurvey(randomSurveyDto);

			when(targetFindPort.getTargetId(surveyId)).thenReturn(targetId);
			when(surveyFindPort.getSurvey(surveyId)).thenReturn(survey);

			SurveyDto surveyDto = surveyFindService.findSurvey(surveyId);

			assertAll(
				() -> assertNotNull(surveyDto),
				() -> assertEquals(targetId, surveyDto.getTargetId()),
				() -> assertEquals(survey, SurveyDtoMapper.toSurvey(surveyDto))
			);
		}
	}
}
