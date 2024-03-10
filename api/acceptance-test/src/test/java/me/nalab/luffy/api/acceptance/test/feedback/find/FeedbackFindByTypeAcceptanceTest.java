package me.nalab.luffy.api.acceptance.test.feedback.find;

import static me.nalab.luffy.api.acceptance.test.feedback.FeedbackAcceptanceValidator.*;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
import me.nalab.luffy.api.acceptance.test.feedback.AbstractFeedbackTestSupporter;
import me.nalab.luffy.api.acceptance.test.feedback.create.request.AbstractQuestionFeedbackRequest;
import me.nalab.luffy.api.acceptance.test.feedback.create.request.ChoiceQuestionFeedbackRequest;
import me.nalab.luffy.api.acceptance.test.feedback.create.request.FeedbackCreateRequest;
import me.nalab.luffy.api.acceptance.test.feedback.create.request.ReviewerRequest;
import me.nalab.luffy.api.acceptance.test.feedback.create.request.ShortQuestionFeedbackRequest;
import me.nalab.luffy.api.acceptance.test.feedback.create.response.ChoiceFormQuestionResponse;
import me.nalab.luffy.api.acceptance.test.feedback.create.response.ShortFormQuestionResponse;
import me.nalab.luffy.api.acceptance.test.feedback.create.response.SurveyFindResponse;
import me.nalab.luffy.api.acceptance.test.survey.RequestSample;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("classpath:h2.properties")
@ComponentScan("me.nalab")
@EnableJpaRepositories(basePackages = {"me.nalab"})
@EntityScan(basePackages = {"me.nalab"})
class FeedbackFindByTypeAcceptanceTest extends AbstractFeedbackTestSupporter {

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
	@DisplayName("타입에 해당하는 피드백 조회 성공 인수테스트 - formType이 tendency일 때")
	void FIND_FEEDBACK_BY_FORM_TYPE_TENDENCY() throws Exception {
		// given
		Long targetId = targetInitializer.saveTargetAndGetId("hello world", Instant.now());
		String token = "bearer token";
		applicationEventPublisher.publishEvent(MockUserRegisterEvent.builder()
			.expectedId(targetId)
			.expectedToken(token)
			.build());
		Long surveyId = createAndGetSurveyId(token, RequestSample.DEFAULT_JSON);
		SurveyFindResponse surveyFindResponse = getSurveyFindResponse(surveyId);
		FeedbackCreateRequest feedbackCreateRequest = getFeedbackCreateRequest(surveyFindResponse, true, "developer");
		createFeedback(surveyId, OBJECT_MAPPER.writeValueAsString(feedbackCreateRequest));

		// when
		ResultActions resultActions = findFeedbackByType(surveyId, "tendency");

		// then
		assertIsFeedbackFoundByTendency(resultActions);

	}

	@Test
	@DisplayName("타입에 해당하는 피드백 조회 성공 인수테스트 - formType이 custom일 때")
	void FIND_FEEDBACK_BY_FORM_TYPE_CUSTOM() throws Exception {
		// given
		Long targetId = targetInitializer.saveTargetAndGetId("hello world", Instant.now());
		String token = "bearer token";
		applicationEventPublisher.publishEvent(MockUserRegisterEvent.builder()
			.expectedId(targetId)
			.expectedToken(token)
			.build());
		Long surveyId = createAndGetSurveyId(token, RequestSample.DEFAULT_JSON);
		SurveyFindResponse surveyFindResponse = getSurveyFindResponse(surveyId);
		FeedbackCreateRequest feedbackCreateRequest = getFeedbackCreateRequest(surveyFindResponse, true, "developer");
		createFeedback(surveyId, OBJECT_MAPPER.writeValueAsString(feedbackCreateRequest));

		// when
		ResultActions resultActions = findFeedbackByType(surveyId, "custom");

		// then
		assertIsFeedbackFoundByCustom(resultActions);

	}

	@Test
	@DisplayName("타입에 해당하는 피드백 조회 성공 인수테스트 - 피드백이 없을때")
	void FIND_FEEDBACK_BY_TYPE_TENDENCY_WITH_NO_FEEDBACK() throws Exception {
		// given
		Long targetId = targetInitializer.saveTargetAndGetId("hello world", Instant.now());
		String token = "bearer token";
		applicationEventPublisher.publishEvent(MockUserRegisterEvent.builder()
			.expectedId(targetId)
			.expectedToken(token)
			.build());
		Long surveyId = createAndGetSurveyId(token, RequestSample.DEFAULT_JSON);

		// when
		ResultActions resultActions = findFeedbackByType(surveyId, "tendency");

		// then
		assertIsFeedbackFoundByFormTypeWithNoFeedback(resultActions);

	}

	@Test
	@DisplayName("타입에 해당하는 피드백 조회 성공 인수테스트 - 피드백이 없을때")
	void FIND_FEEDBACK_BY_TYPE_CUSTOM_WITH_NO_FEEDBACK() throws Exception {
		// given
		Long targetId = targetInitializer.saveTargetAndGetId("hello world", Instant.now());
		String token = "bearer token";
		applicationEventPublisher.publishEvent(MockUserRegisterEvent.builder()
			.expectedId(targetId)
			.expectedToken(token)
			.build());
		Long surveyId = createAndGetSurveyId(token, RequestSample.DEFAULT_JSON);

		// when
		ResultActions resultActions = findFeedbackByType(surveyId, "custom");

		// then
		assertIsFeedbackFoundByFormTypeWithNoFeedback(resultActions);

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

	private static FeedbackCreateRequest getFeedbackCreateRequest(SurveyFindResponse surveyFindResponse,
		boolean collaborationExperience, String position) {
		return FeedbackCreateRequest.builder()
			.reviewerRequest(getReviewerRequest(collaborationExperience, position))
			.abstractQuestionFeedbackRequests(getQuestionFeedbackRequestList(surveyFindResponse))
			.build();
	}

	private static ReviewerRequest getReviewerRequest(boolean collaborationExperience, String position) {
		return ReviewerRequest.builder()
			.collaborationExperience(collaborationExperience)
			.position(position)
			.build();
	}

	private static List<AbstractQuestionFeedbackRequest> getQuestionFeedbackRequestList(
		SurveyFindResponse surveyFindResponse) {

		return surveyFindResponse.getQuestion().stream()
			.map(q -> {
				if (q instanceof ShortFormQuestionResponse) {
					return getShortQuestionFeedbackRequest((ShortFormQuestionResponse)q);
				}
				return getChoiceQuestionFeedbackRequest((ChoiceFormQuestionResponse)q);
			})
			.collect(Collectors.toList());
	}

	private static ShortQuestionFeedbackRequest getShortQuestionFeedbackRequest(
		ShortFormQuestionResponse shortFormQuestionResponse) {
		return ShortQuestionFeedbackRequest.builder()
			.questionId(shortFormQuestionResponse.getQuestionId())
			.replyList(List.of("mocking", "words", "hello!"))
			.type("short")
			.build();
	}

	private static ChoiceQuestionFeedbackRequest getChoiceQuestionFeedbackRequest(
		ChoiceFormQuestionResponse choiceFormQuestionResponse) {
		return ChoiceQuestionFeedbackRequest.builder()
			.type("choice")
			.questionId(choiceFormQuestionResponse.getQuestionId())
			.choiceList(List.of(choiceFormQuestionResponse.getChoices().get(0).getChoiceId()))
			.build();
	}

}
