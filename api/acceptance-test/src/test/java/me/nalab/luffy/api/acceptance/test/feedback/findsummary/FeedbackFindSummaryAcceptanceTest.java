package me.nalab.luffy.api.acceptance.test.feedback.findsummary;

import static me.nalab.luffy.api.acceptance.test.feedback.FeedbackAcceptanceValidator.assertIsFeedbackSummaryFound;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
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
class FeedbackFindSummaryAcceptanceTest extends AbstractFeedbackTestSupporter {

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
	@DisplayName("질문 폼의 전체 피드백 수와 읽지 않은 피드백 수 조회 - 피드백이 없는 경우")
	void FEEFBACK_SUMMARY_FIND_SUCCESS_TEST_WITH_NO_FEEDBACK() throws Exception {

		// given
		String token = "bearer token";
		Long targetId = targetInitializer.saveTargetAndGetId("sujin", Instant.now());
		applicationEventPublisher.publishEvent(
			MockUserRegisterEvent.builder().expectedToken(token).expectedId(targetId).build());
		Long surveyId = createSurveyAndGetSurveyId(token, RequestSample.DEFAULT_JSON);

		// when
		ResultActions resultActions = findFeedbackSummary(token, surveyId.toString());

		// then
		assertIsFeedbackSummaryFound(resultActions);
	}

	@Test
	@DisplayName("질문 폼의 전체 피드백 수와 읽지 않은 피드백 수 조회 - 피드백이 있는 경우")
	void FEEFBACK_SUMMARY_FIND_SUCCESS_TEST_WITH_FEEDBACK() throws Exception {

		// given
		String token = "bearer token";
		Long targetId = targetInitializer.saveTargetAndGetId("sujin", Instant.now());
		applicationEventPublisher.publishEvent(
			MockUserRegisterEvent.builder().expectedToken(token).expectedId(targetId).build());

		Long surveyId = createSurveyAndGetSurveyId(token, RequestSample.DEFAULT_JSON);

		SurveyFindResponse surveyFindResponse = getSurveyFindResponse(surveyId);
		FeedbackCreateRequest feedbackCreateRequest = getFeedbackCreateRequest(surveyFindResponse, true, "developer");
		createFeedback(surveyId, OBJECT_MAPPER.writeValueAsString(feedbackCreateRequest));

		// when
		ResultActions resultActions = findFeedbackSummary(token, surveyId.toString());

		// then
		assertIsFeedbackSummaryFound(resultActions);
	}

	private Long createSurveyAndGetSurveyId(String token, String content) throws Exception {

		String stringResponse = createSurvey(token, content).andReturn()
			.getResponse()
			.getContentAsString();

		JSONObject jsonObject = new JSONObject(stringResponse);
		return Long.valueOf(jsonObject.getString("survey_id"));
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
			.type("choice")
			.questionId(choiceFormQuestionResponse.getQuestionId())
			.choiceList(List.of(choiceFormQuestionResponse.getChoices().get(0).getChoiceId()))
			.build();
	}

}
