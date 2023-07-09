package me.nalab.luffy.api.acceptance.test.feedback.find;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.nalab.auth.mock.api.MockUserRegisterEvent;
import me.nalab.luffy.api.acceptance.test.TargetInitializer;
import me.nalab.luffy.api.acceptance.test.feedback.AbstractFeedbackTestSupporter;
import me.nalab.luffy.api.acceptance.test.feedback.create.request.FeedbackCreateRequest;
import me.nalab.luffy.api.acceptance.test.feedback.create.response.SurveyFindResponse;
import me.nalab.luffy.api.acceptance.test.survey.RequestSample;
import me.nalab.survey.web.adaptor.findfeedback.response.QuestionFeedbackResponse;
import me.nalab.survey.web.adaptor.findfeedback.response.survey.ShortSurveyResponse;
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

import java.time.Instant;
import java.util.Map;

import static me.nalab.luffy.api.acceptance.test.feedback.FeedbackAcceptanceValidator.*;
import static me.nalab.luffy.api.acceptance.test.feedback.FeedbackCreateRequestFixture.getFeedbackCreateRequest;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("classpath:h2.properties")
@ComponentScan("me.nalab")
@EnableJpaRepositories(basePackages = {"me.nalab"})
@EntityScan(basePackages = {"me.nalab"})
class FeedbackFindAcceptanceTest extends AbstractFeedbackTestSupporter {

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@Autowired
	private TargetInitializer targetInitializer;

	private static final ObjectMapper OBJECT_MAPPER;

	static {
		OBJECT_MAPPER = new ObjectMapper();
		OBJECT_MAPPER.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
	}

	@Test
	@DisplayName("피드백 조회 성공 인수테스트")
	void FIND_FEED_BACK_SUCCESS() throws Exception {
		// given
		Long targetId = targetInitializer.saveTargetAndGetId("hello world", Instant.now());
		String token = "mock token";
		applicationEventPublisher.publishEvent(MockUserRegisterEvent.builder()
			.expectedId(targetId)
			.expectedToken(token)
			.build());

		Long surveyId = createAndGetSurveyId(token, RequestSample.DEFAULT_JSON);
		SurveyFindResponse surveyFindResponse = getSurveyFindResponse(surveyId);
		FeedbackCreateRequest feedbackCreateRequest = getFeedbackCreateRequest(surveyFindResponse, true, "developer");

		createFeedback(surveyId, OBJECT_MAPPER.writeValueAsString(feedbackCreateRequest));

		// when
		ResultActions resultActions = findFeedback(token, surveyId);

		// then
		assertIsFeedbackFound(resultActions);
	}

	@Test
	@DisplayName("피드백 조회 성공 인수테스트 - 피드백이 없을때")
	void FIND_FEED_BACK_SUCCESS_ANY_FEEDBACK() throws Exception {
		// given
		Long targetId = targetInitializer.saveTargetAndGetId("hello world", Instant.now());
		String token = "mock token";
		applicationEventPublisher.publishEvent(MockUserRegisterEvent.builder()
			.expectedId(targetId)
			.expectedToken(token)
			.build());

		Long surveyId = createAndGetSurveyId(token, RequestSample.DEFAULT_JSON);

		// when
		ResultActions resultActions = findFeedback(token, surveyId);

		// then
		assertIsFeedbackNotFound(resultActions);
	}

	@Test
	@DisplayName("북마크된 피드백 조회 성공 인수테스트")
	void FIND_BOOKMARKED_FEED_BACK_SUCCESS() throws Exception {
		// given
		Long surveyId = setUpSurveyAndFeedbackAndBookmark();

		// when
		ResultActions resultActions = findBookmarkedFeedback(surveyId);

		// then
		assertIsBookmarkedFeedbackFound(resultActions);
	}

	@Test
	@DisplayName("북마크된 피드백 조회 성공 인수테스트 - 피드백이 없을때")
	void FIND_BOOKMARKED_FEED_BACK_SUCCESS_ANY_FEEDBACK() throws Exception {
		// given
		Long targetId = targetInitializer.saveTargetAndGetId("hello world", Instant.now());
		String token = "mock token";
		applicationEventPublisher.publishEvent(MockUserRegisterEvent.builder()
																	.expectedId(targetId)
																	.expectedToken(token)
																	.build());

		Long surveyId = createAndGetSurveyId(token, RequestSample.DEFAULT_JSON);

		// when
		ResultActions resultActions = findBookmarkedFeedback(surveyId);

		// then
		assertIsBookmarkedFeedbackNotFound(resultActions);
	}

	private Long setUpSurveyAndFeedbackAndBookmark() throws Exception {
		Long targetId = targetInitializer.saveTargetAndGetId("hello world", Instant.now());
		String token = "mock token";
		applicationEventPublisher.publishEvent(MockUserRegisterEvent.builder()
																	.expectedId(targetId)
																	.expectedToken(token)
																	.build());

		Long surveyId = createAndGetSurveyId(token, RequestSample.DEFAULT_JSON);
		SurveyFindResponse surveyFindResponse = getSurveyFindResponse(surveyId);
		FeedbackCreateRequest feedbackCreateRequest = getFeedbackCreateRequest(surveyFindResponse, true, "developer");
		createFeedback(surveyId, OBJECT_MAPPER.writeValueAsString(feedbackCreateRequest));

		String stringResponse = findFeedback(token, surveyId).andReturn()
															 .getResponse()
															 .getContentAsString();
		QuestionFeedbackResponse response = OBJECT_MAPPER.readValue(stringResponse, QuestionFeedbackResponse.class);
		String feedbackId = response.getAbstractSurveyResponse()
				.stream()
				.filter(abstractSurveyResponse -> {
					if (abstractSurveyResponse instanceof ShortSurveyResponse) {
						ShortSurveyResponse shortSurveyResponse = (ShortSurveyResponse) abstractSurveyResponse;
						return !shortSurveyResponse.getShortFeedbackResponseList().isEmpty();
					}
					return false;
				})
				.map(abstractSurveyResponse -> {
					ShortSurveyResponse shortSurveyResponse = (ShortSurveyResponse) abstractSurveyResponse;
					return shortSurveyResponse.getShortFeedbackResponseList().get(0).getId();
				})
				.findFirst()
				.orElseThrow();
		//FIXME 피드백 id로 피드백 북마크하기
		return surveyId;
	}

	private Long createAndGetSurveyId(String token, String content) throws Exception {
		ResultActions resultActions = createSurvey(token, content);
		Map<String, Long> surveyIdMap = OBJECT_MAPPER.readValue(
			resultActions.andReturn().getResponse().getContentAsString(), new TypeReference<>() {
			});
		return surveyIdMap.get("survey_id");
	}

	private SurveyFindResponse getSurveyFindResponse(Long surveyId) throws Exception {
		ResultActions resultActions = findSurvey(surveyId);
		return OBJECT_MAPPER.readValue(resultActions.andReturn().getResponse().getContentAsString(),
			SurveyFindResponse.class);
	}

}
