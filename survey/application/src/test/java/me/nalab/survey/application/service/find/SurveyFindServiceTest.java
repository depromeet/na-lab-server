package me.nalab.survey.application.service.find;

import static me.nalab.survey.application.RandomSurveyDtoFixture.createRandomSurveyDto;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import me.nalab.survey.application.common.dto.SurveyDto;
import me.nalab.survey.application.common.mapper.SurveyDtoMapper;
import me.nalab.survey.application.port.out.persistence.survey.find.SurveyFindPort;
import me.nalab.survey.application.port.out.persistence.target.find.TargetFindPort;
import me.nalab.survey.domain.survey.Survey;

class SurveyFindServiceTest {

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
	void SURVEY_FIND_SERVICE_TEST() {
		SurveyDto randomSurveyDto = createRandomSurveyDto();
		Long surveyId = randomSurveyDto.getId();
		Long targetId = randomSurveyDto.getTargetId();
		Survey survey = SurveyDtoMapper.toSurvey(randomSurveyDto);

		when(targetFindPort.findTargetIdBySurveyId(surveyId)).thenReturn(Optional.of(targetId));
		when(surveyFindPort.findSurvey(surveyId)).thenReturn(Optional.of(survey));

		SurveyDto result = surveyFindService.findSurvey(surveyId);

		assertNotNull(result);
		assertEquals(targetId, result.getTargetId());
		assertEquals(surveyId, result.getId());
	}

	@Test
	void SURVEY_FIND_FAIL_SERVICE_TEST() {
		Long surveyId = 2L;

		when(targetFindPort.findTargetIdBySurveyId(surveyId)).thenReturn(Optional.empty());
		when(surveyFindPort.findSurvey(surveyId)).thenReturn(Optional.empty());

		assertThrows(IllegalStateException.class, () -> surveyFindService.findSurvey(surveyId));
	}
}
