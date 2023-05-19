package me.nalab.survey.application.service.findid;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
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

import me.nalab.survey.application.exception.EmptySurveyIdListException;
import me.nalab.survey.application.port.in.web.findid.SurveyIdFindUseCase;
import me.nalab.survey.application.port.out.persistence.findid.SurveyIdFindPort;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SurveyIdFindService.class)
class SurveyIdFindServiceTest {

	@Autowired
	private SurveyIdFindUseCase surveyIdFindUseCase;

	@MockBean
	private SurveyIdFindPort surveyIdFindPort;

	@Test
	@DisplayName("SurveyId List 조회 성공 테스트")
	void GET_SURVEY_ID_LIST_SUCCESS() {
		// given
		List<Long> expectedSurveyIdList = List.of(1L, 2L, 3L);

		// when
		when(surveyIdFindPort.findSurveyIdByTargetId(anyLong())).thenReturn(expectedSurveyIdList);

		List<Long> resultSurveyIdList = surveyIdFindUseCase.getSurveyIdByTargetId(1L);

		// then
		assertIterableEquals(expectedSurveyIdList, resultSurveyIdList);
	}

	@Test
	@DisplayName("SurveyId List 조회 실패 테스트 - List가 비어있음")
	void GET_SURVEY_ID_LIST_FAIL_EMPTY_LIST() {
		// given
		List<Long> expectedSurveyIdList = List.of();

		// when
		when(surveyIdFindPort.findSurveyIdByTargetId(anyLong())).thenReturn(expectedSurveyIdList);

		// then
		assertThrows(EmptySurveyIdListException.class, () -> surveyIdFindUseCase.getSurveyIdByTargetId(1L));
	}

	@Test
	@DisplayName("SurveyId List 조회 실패 테스트 - List가 null")
	void GET_SURVEY_ID_LIST_FAIL_NULL_LIST() {
		// when
		when(surveyIdFindPort.findSurveyIdByTargetId(anyLong())).thenReturn(null);

		// then
		assertThrows(EmptySurveyIdListException.class, () -> surveyIdFindUseCase.getSurveyIdByTargetId(1L));
	}

}
