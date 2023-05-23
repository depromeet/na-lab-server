package me.nalab.luffy.api.acceptance.test.feedback.create;

import static me.nalab.luffy.api.acceptance.test.feedback.FeedbackAcceptanceValidator.assertIsFeedbackCreated;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
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
class FeedbackCreateAcceptanceTest extends AbstractFeedbackTestSupporter {

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
	@DisplayName("Survey에 feedback 생성 성공 테스트")
	void CREATE_FEEDBACK_TO_SURVEY_SUCCESS() throws Exception {
		// given
		Long targetId = targetInitializer.saveTargetAndGetId("nalab", LocalDateTime.now());
		String token = "nalab-token";
		applicationEventPublisher.publishEvent(MockUserRegisterEvent.builder()
			.expectedToken(token)
			.expectedId(targetId)
			.build());

		Long surveyId = getSurveyId(token, RequestSample.DEFAULT_JSON);
		SurveyFindResponse surveyFindResponse = getSurveyFindResponse(surveyId);
		FeedbackCreateRequest feedbackCreateRequest = getFeedbackCreateRequest(surveyFindResponse, true, "developer");

		// when
		ResultActions resultActions = createFeedback(surveyId, OBJECT_MAPPER.writeValueAsString(feedbackCreateRequest));

		// then
		assertIsFeedbackCreated(resultActions);
	}

	@Test
	@DisplayName("Survey에 feedback 생성 성공 테스트 - 여러명이 feedback")
	void CREATE_MULTIPLE_FEEDBACK_TO_SURVEY_SUCCESS() throws Exception {
		// given
		Long targetId = targetInitializer.saveTargetAndGetId("hello", LocalDateTime.now());
		String token = "hello-token";
		applicationEventPublisher.publishEvent(MockUserRegisterEvent.builder()
			.expectedToken(token)
			.expectedId(targetId)
			.build());

		Long surveyId = getSurveyId(token, RequestSample.DEFAULT_JSON);
		SurveyFindResponse surveyFindResponse = getSurveyFindResponse(surveyId);
		List<FeedbackCreateRequest> feedbackCreateRequestList = List.of(
			getFeedbackCreateRequest(surveyFindResponse, true, "developer"),
			getFeedbackCreateRequest(surveyFindResponse, false, "designer"),
			getFeedbackCreateRequest(surveyFindResponse, false, "other"),
			getFeedbackCreateRequest(surveyFindResponse, true, "product-manager")
		);

		// when
		List<ResultActions> resultActionsList = feedbackCreateRequestList.stream().map(
			r -> {
				try {
					return createFeedback(surveyId, OBJECT_MAPPER.writeValueAsString(r));
				} catch(Exception e) {
					fail(e.getMessage());
				}
				throw new IllegalStateException("Cannot reach this line");
			}
		).collect(Collectors.toList());

		// then
		resultActionsList.forEach(r -> {
			try {
				assertIsFeedbackCreated(r);
			} catch(Exception e) {
				fail(e.getMessage());
			}
		});
	}

	private Long getSurveyId(String token, String content) throws Exception {
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

	private FeedbackCreateRequest getFeedbackCreateRequest(SurveyFindResponse surveyFindResponse,
		boolean collaborationExperience, String position) {
		return FeedbackCreateRequest.builder()
			.reviewerRequest(getReviewerRequest(collaborationExperience, position))
			.abstractQuestionFeedbackRequests(getQuestionFeedbackRequestList(surveyFindResponse))
			.build();
	}

	private ReviewerRequest getReviewerRequest(boolean collaborationExperience, String position) {
		return ReviewerRequest.builder()
			.collaborationExperience(collaborationExperience)
			.position(position)
			.build();
	}

	private List<AbstractQuestionFeedbackRequest> getQuestionFeedbackRequestList(
		SurveyFindResponse surveyFindResponse) {

		return surveyFindResponse.getQuestion().stream()
			.map(q -> {
				if(q instanceof ShortFormQuestionResponse) {
					return getShortQuestionFeedbackRequest((ShortFormQuestionResponse)q);
				}
				return getChoiceQuestionFeedbackRequest((ChoiceFormQuestionResponse)q);
			})
			.collect(Collectors.toList());
	}

	private ShortQuestionFeedbackRequest getShortQuestionFeedbackRequest(
		ShortFormQuestionResponse shortFormQuestionResponse) {
		return ShortQuestionFeedbackRequest.builder()
			.questionId(shortFormQuestionResponse.getQuestionId())
			.replyList(List.of("mocking", "words", "hello!"))
			.type("short")
			.build();
	}

	private ChoiceQuestionFeedbackRequest getChoiceQuestionFeedbackRequest(
		ChoiceFormQuestionResponse choiceFormQuestionResponse) {
		return ChoiceQuestionFeedbackRequest.builder()
			.questionId(choiceFormQuestionResponse.getQuestionId())
			.choiceList(List.of(choiceFormQuestionResponse.getChoices().get(0).getChoiceId()))
			.build();
	}

}
