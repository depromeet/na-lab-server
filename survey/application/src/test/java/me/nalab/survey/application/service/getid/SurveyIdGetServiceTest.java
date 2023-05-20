package me.nalab.survey.application.service.getid;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import me.nalab.survey.application.exception.TargetDoesNotHasSurveyException;
import me.nalab.survey.application.port.in.web.getid.SurveyIdGetUseCase;
import me.nalab.survey.application.port.out.persistence.findid.SurveyIdFindPort;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SurveyIdGetService.class)
class SurveyIdGetServiceTest {

	@Autowired
	private SurveyIdGetUseCase surveyIdGetUseCase;

	@MockBean
	private SurveyIdFindPort surveyIdFindPort;

	@Test
	@DisplayName("SurveyId List 조회 성공 테스트")
	void GET_SURVEY_ID_LIST_SUCCESS() {
		// given
		List<Long> expectedSurveyIdList = List.of(1L);

		// when
		when(surveyIdFindPort.findAllSurveyIdByTargetId(anyLong())).thenReturn(expectedSurveyIdList);

		Long result = surveyIdGetUseCase.getSurveyIdByTargetId(1L);

		// then
		assertEquals(expectedSurveyIdList.get(0), result);
	}

	@Test
	@DisplayName("SurveyId List 조회 실패 테스트 - List가 비어있음")
	void GET_SURVEY_ID_LIST_FAIL_EMPTY_LIST() {
		// given
		List<Long> expectedSurveyIdList = List.of();

		// when
		when(surveyIdFindPort.findAllSurveyIdByTargetId(anyLong())).thenReturn(expectedSurveyIdList);

		// then
		assertThrows(TargetDoesNotHasSurveyException.class, () -> surveyIdGetUseCase.getSurveyIdByTargetId(1L));
	}

	@Test
	@DisplayName("SurveyId List 조회 실패 테스트 - List가 null")
	void GET_SURVEY_ID_LIST_FAIL_NULL_LIST() {
		// when
		when(surveyIdFindPort.findAllSurveyIdByTargetId(anyLong())).thenReturn(null);

		// then
		assertThrows(TargetDoesNotHasSurveyException.class, () -> surveyIdGetUseCase.getSurveyIdByTargetId(1L));
	}

}
