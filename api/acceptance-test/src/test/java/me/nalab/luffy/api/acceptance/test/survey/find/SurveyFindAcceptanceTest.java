package me.nalab.luffy.api.acceptance.test.survey.find;

import static me.nalab.luffy.api.acceptance.test.survey.SurveyAcceptanceValidator.assertIsSurveyAndQuestionFound;

import java.time.LocalDateTime;

import org.json.JSONObject;
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
class SurveyFindAcceptanceTest extends AbstractSurveyTestSupporter {

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@Autowired
	private TargetInitializer targetInitializer;

	@Test
	void SURVEY_FIND_SUCCESS_TEST() throws Exception {

		String token = "luffy's-double-token";
		Long targetId = targetInitializer.saveTargetAndGetId("sujin", LocalDateTime.now());
		applicationEventPublisher.publishEvent(
			MockUserRegisterEvent.builder().expectedToken(token).expectedId(targetId).build());

		String stringResponse = createSurvey(token, RequestSample.DEFAULT_JSON).andReturn()
			.getResponse()
			.getContentAsString();

		JSONObject jsonObject = new JSONObject(stringResponse);
		Long surveyId = jsonObject.getLong("survey_id");

		ResultActions resultActions = findSurvey(surveyId);

		assertIsSurveyAndQuestionFound(resultActions);
	}
}
