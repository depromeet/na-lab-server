package me.nalab.survey.application.service.survey.find;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import me.nalab.survey.application.common.dto.SurveyDto;
import me.nalab.survey.application.port.out.persistence.survey.find.SurveyFindPort;

class SurveyFindServiceTest {

	@Mock
	private SurveyFindPort surveyFindPort;

	private SurveyFindService surveyFindService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		surveyFindService = new SurveyFindService(surveyFindPort);
	}

	@Test
	void surveyFindService() {
		// given
		Long surveyId = 1L;
		SurveyDto expectedSurveyDto = SurveyDto.builder()
			.id(surveyId)
			.build();
		when(surveyFindPort.getSurvey(surveyId)).thenReturn(expectedSurveyDto);

		// when
		SurveyDto actualSurveyDto = surveyFindService.findSurvey(surveyId);

		// then
		assertEquals(expectedSurveyDto, actualSurveyDto);
	}
}
