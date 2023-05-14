package me.nalab.survey.application.port.service;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import me.nalab.survey.application.port.TestIdGenerator;
import me.nalab.survey.application.port.in.create.CreateSurveyUseCase;
import me.nalab.survey.application.port.in.create.SurveyCreateRequest;
import me.nalab.survey.application.port.out.persistence.CreateSurveyPort;
import me.nalab.survey.application.port.out.persistence.FindSurveyPort;

import static me.nalab.survey.application.port.fixture.CreateSurveyFixture.*;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestIdGenerator.class, CreateSurveyService.class})
class CreateSurveyServiceTest {

	@Autowired
	private CreateSurveyUseCase createSurveyUseCase;

	@MockBean
	private CreateSurveyPort createSurveyPort;

	@MockBean
	private FindSurveyPort findSurveyPort;

	@Test
	@DisplayName("Survey 생성 성공 테스트")
	void CREATE_SURVEY_SUCCESS(){
		// given
		SurveyCreateRequest surveyCreateRequest = getSurveyCreateRequest(
			3, List.of(
				getChoiceQuestionRequest("hello-world1", 3, 1, List.of(
					getChoiceRequest(1, "first"),
					getChoiceRequest(2, "second"),
					getChoiceRequest(3, "third")
				)),
				getShortQuestionRequest(2, "hello-world1"),
				getShortQuestionRequest(3, "hello-world2")
			)
		);

		// when
		assertDoesNotThrow(() -> createSurveyUseCase.createSurvey(surveyCreateRequest));
	}

	@Test
	@DisplayName("가장 최근 만들어진 Survey id 조회 성공 테스트")
	void FIND_LATEST_SURVEY_ID_SUCCESS(){
		// given
		Long expected = 123456789L;

		// when
		when(findSurveyPort.findLatestSurveyId(anyLong())).thenReturn(expected);

		// then
		assertEquals(expected, findSurveyPort.findLatestSurveyId(expected));
	}

}
