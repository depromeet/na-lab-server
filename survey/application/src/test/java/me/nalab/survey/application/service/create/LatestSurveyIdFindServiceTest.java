package me.nalab.survey.application.service.create;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import me.nalab.survey.application.port.in.web.LatestSurveyIdFindUseCase;
import me.nalab.survey.application.port.out.persistence.LatestSurveyIdFindPort;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = LatestSurveyIdFindService.class)
class LatestSurveyIdFindServiceTest {

	@MockBean
	private LatestSurveyIdFindPort latestSurveyIdFindPort;

	@Autowired
	private LatestSurveyIdFindUseCase latestSurveyIdFindUseCase;

	@Test
	@DisplayName("최근 생성된 survey id 조회 성공 테스트")
	void FIND_LATEST_SURVEY_ID_SUCCESS() {
		// given
		Long targetId = 1L;
		Long surveyId = 12345677890L;

		// when
		when(latestSurveyIdFindPort.getLatestSurveyIdByTargetId(targetId)).thenReturn(Optional.of(surveyId));

		// then
		assertEquals(surveyId, latestSurveyIdFindUseCase.getLatestSurveyIdByTaregetId(targetId));
	}

	@Test
	@DisplayName("최근 생성된 survey id 조회 실패 테스트 - 생성된 survey가 없음")
	void FIND_LATEST_SURVEY_ID_FAIL_NO_SURVEYS() {
		// given
		Long targetId = 1L;
		Long surveyId = 1234567890L;

		// when
		when(latestSurveyIdFindPort.getLatestSurveyIdByTargetId(targetId)).thenReturn(Optional.empty());

		// then
		assertThrows(IllegalStateException.class,
			() -> latestSurveyIdFindUseCase.getLatestSurveyIdByTaregetId(targetId));
	}

}
