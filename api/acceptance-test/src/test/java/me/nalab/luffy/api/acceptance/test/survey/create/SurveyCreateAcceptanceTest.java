package me.nalab.luffy.api.acceptance.test.survey.create;

import static me.nalab.luffy.api.acceptance.test.survey.SurveyAcceptanceValidator.assertIsSurveyCreateIsFailed;
import static me.nalab.luffy.api.acceptance.test.survey.SurveyAcceptanceValidator.assertIsSurveyCreated;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
class SurveyCreateAcceptanceTest extends AbstractSurveyTestSupporter {

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@Autowired
	private TargetInitializer targetInitializer;

	@PersistenceContext
	private EntityManager entityManager;

	@Test
	@DisplayName("새로운 Survey 생성 성공 테스트 - 기본 질문")
	void CREATE_NEW_SURVEY_SUCCESS_DEFAULT() throws Exception {
		// given
		Long targetId = targetInitializer.saveTargetAndGetId("luffy",
			LocalDateTime.now().minusYears(24).toInstant(ZoneOffset.UTC));
		String token = "bearer luffy's-double-token";
		applicationEventPublisher.publishEvent(MockUserRegisterEvent.builder()
			.expectedToken(token)
			.expectedId(targetId)
			.build());

		// when
		ResultActions resultActions = createSurvey(token, RequestSample.DEFAULT_JSON);

		// then
		assertIsSurveyCreated(resultActions);
	}

	@Test
	@DisplayName("새로운 Survey 생성 성공 테스트 - 커스텀 질문 포함")
	void CREATE_NEW_SURVEY_SUCCESS_SUCCESS_CUSTOM() throws Exception {
		// given
		Long targetId = targetInitializer.saveTargetAndGetId("luffy",
			LocalDateTime.now().minusYears(24).toInstant(ZoneOffset.UTC));
		String token = "bearer luffy's-double-token";
		applicationEventPublisher.publishEvent(MockUserRegisterEvent.builder()
			.expectedToken(token)
			.expectedId(targetId)
			.build());

		// when
		ResultActions resultActions = createSurvey(token, RequestSample.CUSTOM_JSON);

		// then
		assertIsSurveyCreated(resultActions);
	}

	@Test
	@DisplayName("새로운 Survey 생성 시 Valid에 걸리면 예외가 발생한다")
	void CREATE_NEW_SURVEY_WITH_FAIL() throws Exception {
		// given
		Long targetId = targetInitializer.saveTargetAndGetId("luffy", LocalDateTime.now().minusYears(24).toInstant(ZoneOffset.UTC));
		String token = "bearer luffy's-double-token";
		applicationEventPublisher.publishEvent(MockUserRegisterEvent.builder()
			.expectedToken(token)
			.expectedId(targetId)
			.build());

		// when
		ResultActions resultActions = createSurvey(token, RequestSample.FAILED_JSON);

		// then
		assertIsSurveyCreateIsFailed(resultActions);

	}

}
