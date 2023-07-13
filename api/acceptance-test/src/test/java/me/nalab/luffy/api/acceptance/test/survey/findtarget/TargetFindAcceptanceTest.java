package me.nalab.luffy.api.acceptance.test.survey.findtarget;

import static me.nalab.luffy.api.acceptance.test.survey.SurveyAcceptanceValidator.*;

import java.time.Instant;
import java.util.Map;

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

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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
class TargetFindAcceptanceTest extends AbstractSurveyTestSupporter {

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@Autowired
	private TargetInitializer targetInitializer;

	@PersistenceContext
	private EntityManager entityManager;

	private static final ObjectMapper OBJECT_MAPPER;

	static {
		OBJECT_MAPPER = new ObjectMapper();
		OBJECT_MAPPER.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
	}

	@Test
	@DisplayName("survey_id에 해당하는 타겟의 정보 조회")
	void FIND_TARGET_BY_SURVEY_ID_WITH_NO_AUTHORIZATION() throws Exception {
		// given
		Long targetId = targetInitializer.saveTargetAndGetId("target", Instant.now());
		String token = "token";
		applicationEventPublisher.publishEvent(MockUserRegisterEvent.builder()
			.expectedToken(token)
			.expectedId(targetId)
			.build());
		Long surveyId = createAndGetSurveyId(token, RequestSample.DEFAULT_JSON);

		// when
		ResultActions resultActions = findTargetBySurveyId(surveyId);

		// then
		assertIsTargetFound(resultActions);
	}

	private Long createAndGetSurveyId(String token, String content) throws Exception {
		ResultActions resultActions = createSurvey(token, content);
		Map<String, Long> surveyIdMap = OBJECT_MAPPER.readValue(
			resultActions.andReturn().getResponse().getContentAsString(), new TypeReference<>() {
			});
		return surveyIdMap.get("survey_id");
	}

}
