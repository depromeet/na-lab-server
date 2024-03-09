package me.nalab.luffy.api.acceptance.test.survey.exists;

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
import me.nalab.luffy.api.acceptance.test.survey.SurveyAcceptanceValidator;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("classpath:h2.properties")
@ComponentScan("me.nalab")
@EnableJpaRepositories(basePackages = "me.nalab")
@EntityScan(basePackages = "me.nalab")
class SurveyExistsAcceptanceTest extends AbstractSurveyTestSupporter {

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@Autowired
	private TargetInitializer targetInitializer;

	@Test
	@DisplayName("token에 해당하는 survey가 존재한다면, true를 반환한다.")
	void RETURN_TRUE_IF_SURVEY_EXISTS() throws Exception {
		// given
		String token = "bearer luffy's-double-token";
		Long targetId = targetInitializer.saveTargetAndGetId("devxb", Instant.now());
		applicationEventPublisher.publishEvent(MockUserRegisterEvent.builder()
			.expectedToken(token)
			.expectedId(targetId)
			.build()
		);

		createSurvey(token, RequestSample.DEFAULT_JSON);

		// when
		ResultActions result = existsSurveyByToken(token);

		// then
		SurveyAcceptanceValidator.assertIsSurveyExists(result);
	}

	@Test
	@DisplayName("token에 해당하는 survey가 존재하지 않는다면, false를 반환한다.")
	void RETURN_FALSE_IF_SURVEY_DOES_NOT_EXISTS() throws Exception {
		// given
		String token = "bearer luffy's-double-token";
		Long targetId = targetInitializer.saveTargetAndGetId("devxb", Instant.now());
		applicationEventPublisher.publishEvent(MockUserRegisterEvent.builder()
			.expectedToken(token)
			.expectedId(targetId)
			.build()
		);

		// when
		ResultActions result = existsSurveyByToken(token);

		// then
		SurveyAcceptanceValidator.assertIsSurveyDoesNotExists(result);
	}

}
