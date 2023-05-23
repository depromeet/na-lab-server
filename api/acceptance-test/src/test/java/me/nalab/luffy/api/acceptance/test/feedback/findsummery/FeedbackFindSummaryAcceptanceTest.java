package me.nalab.luffy.api.acceptance.test.feedback.findsummery;

import static me.nalab.luffy.api.acceptance.test.feedback.FeedbackAcceptanceValidator.assertIsFeedbackSummaryFound;

import java.time.LocalDateTime;

import org.json.JSONObject;
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
import me.nalab.luffy.api.acceptance.test.feedback.AbstractFeedbackTestSupporter;
import me.nalab.luffy.api.acceptance.test.survey.RequestSample;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("classpath:h2.properties")
@ComponentScan("me.nalab")
@EnableJpaRepositories(basePackages = {"me.nalab"})
@EntityScan(basePackages = {"me.nalab"})
public class FeedbackFindSummaryAcceptanceTest extends AbstractFeedbackTestSupporter {

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@Autowired
	private TargetInitializer targetInitializer;

	@Test
	@DisplayName("질문 폼의 전체 피드백 수와 읽지 않은 피드백 수 조회")
	void FEEFBACK_SUMMARY_FIND_SUCCESS_TEST() throws Exception {

		String token = "luffy's-double-token";
		Long targetId = targetInitializer.saveTargetAndGetId("sujin", LocalDateTime.now());
		applicationEventPublisher.publishEvent(
			MockUserRegisterEvent.builder().expectedToken(token).expectedId(targetId).build());

		Long surveyId = createSurveyAndGetSurveyId(token, RequestSample.DEFAULT_JSON);

		ResultActions resultActions = findFeedbackSummary(token, surveyId.toString());

		assertIsFeedbackSummaryFound(resultActions);
	}

	private Long createSurveyAndGetSurveyId(String token, String content) throws Exception {
		String stringResponse = createSurvey(token, content).andReturn()
			.getResponse()
			.getContentAsString();

		JSONObject jsonObject = new JSONObject(stringResponse);
		return jsonObject.getLong("survey_id");
	}
}
