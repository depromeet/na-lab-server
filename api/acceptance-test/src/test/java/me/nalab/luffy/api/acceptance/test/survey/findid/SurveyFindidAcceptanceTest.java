package me.nalab.luffy.api.acceptance.test.survey.findid;

import static me.nalab.luffy.api.acceptance.test.survey.SurveyAcceptanceValidator.assertIsSurveyFound;
import static me.nalab.luffy.api.acceptance.test.survey.SurveyAcceptanceValidator.assertIsTargetDoesNotHasAnySurvey;

import java.time.Instant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.ResultActions;

import me.nalab.auth.mock.api.MockUserRegisterEvent;
import me.nalab.luffy.api.acceptance.test.TargetInitializer;
import me.nalab.luffy.api.acceptance.test.survey.AbstractSurveyTestSupporter;
import me.nalab.luffy.api.acceptance.test.survey.RequestSample;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("classpath:h2.properties")
@ComponentScan("me.nalab")
@EnableJpaRepositories(basePackages = {"me.nalab"})
@EntityScan(basePackages = {"me.nalab"})
class SurveyFindidAcceptanceTest extends AbstractSurveyTestSupporter {

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@Autowired
	private TargetInitializer targetInitializer;

	@Test
	@DisplayName("로그인된 타겟의 survey id 조회 성공 테스트")
	void GET_LOGINED_SURVEY_ID_SUCCESS() throws Exception {
		// given
		Long targetId = targetInitializer.saveTargetAndGetId("nalab", Instant.now());
		String token = "nalab-token";
		applicationEventPublisher.publishEvent(MockUserRegisterEvent.builder()
			.expectedToken(token)
			.expectedId(targetId)
			.build());

		// when
		createSurvey(token, RequestSample.DEFAULT_JSON);
		ResultActions result = getLoginedSurveyId(token);

		// then
		assertIsSurveyFound(result);
	}

	@Test
	@DisplayName("로그인된 타겟의 survey id 조회 실패 테스트 - 생성된 survey가 없음")
	void GET_LOGINED_SURVEY_ID_FAIL_NO_SURVEY() throws Exception {
		// given
		Long targetId = targetInitializer.saveTargetAndGetId("nalab", Instant.now());
		String token = "nalab-token";
		applicationEventPublisher.publishEvent(MockUserRegisterEvent.builder()
			.expectedToken(token)
			.expectedId(targetId)
			.build());

		ResultActions result = getLoginedSurveyId(token);

		// then
		assertIsTargetDoesNotHasAnySurvey(result);
	}

}
