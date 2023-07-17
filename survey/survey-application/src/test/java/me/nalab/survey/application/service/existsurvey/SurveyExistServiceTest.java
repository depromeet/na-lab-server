package me.nalab.survey.application.service.existsurvey;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import me.nalab.survey.application.port.in.web.existsurvey.SurveyExistUseCase;
import me.nalab.survey.application.port.out.persistence.existsurvey.SurveyExistPort;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SurveyExistService.class})
class SurveyExistServiceTest {

	@MockBean
	private SurveyExistPort surveyExistPort;

	@Autowired
	private SurveyExistUseCase surveyExistUseCase;

	@Test
	@DisplayName("surveyExistsUseCase는 targetId에 해당하는 survey가 존재한다면, true를 반환한다.")
	void RETURN_TRUE_IF_SURVEY_EXISTS() {
		// given
		Long targetId = 1L;

		Mockito.when(surveyExistPort.isSurveyExistByTargetId(targetId)).thenReturn(true);

		// when
		boolean result = surveyExistUseCase.isSurveyExistByTargetId(targetId);

		// then
		Assertions.assertThat(result).isTrue();
	}

	@Test
	@DisplayName("surveyExistsUseCase는 targetId에 해당하는 survey가 존재하지 않는다면, false를 반환한다.")
	void RETURN_FALSE_IF_SURVEY_DOES_NOT_EXISTS() {
		// given
		Long targetId = 1L;

		Mockito.when(surveyExistPort.isSurveyExistByTargetId(targetId)).thenReturn(false);

		// when
		boolean result = surveyExistUseCase.isSurveyExistByTargetId(targetId);

		// then
		Assertions.assertThat(result).isFalse();
	}

}
