package me.nalab.luffy.api.acceptance.test.feedback.reviewer.summary;

import static me.nalab.luffy.api.acceptance.test.feedback.FeedbackAcceptanceValidator.assertIsReviewerSummarized;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
class ReviewerSummarizeAcceptanceTest extends AbstractFeedbackTestSupporter {

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
	@DisplayName("Reviewer 요약 조회 성공 인수테스트 - 리뷰어가 없음")
	void FIND_REVIEWER_SUMMARY_SUCCESS_NONE_REVIEWER() throws Exception {
		// given
		String token = saveTargetAndGetToken("james");
		Long surveyId = saveAndGetSurveyId(token, RequestSample.DEFAULT_JSON);

		// when
		ResultActions resultActions = summarizeReviewer(token, surveyId);

		// then
		assertIsReviewerSummarized(resultActions);
	}

	@Test
	@DisplayName("Reviewer 요약 조회 성공 인수테스트 - 리뷰어 존재")
	void FIND_REVIEWER_SUMMARY_SUCCESS() throws Exception {
		// given
		String token = saveTargetAndGetToken("nalab");
		Long surveyId = saveAndGetSurveyId(token, RequestSample.DEFAULT_JSON);

		SurveyFindResponse surveyFindResponse = getSurveyFindResponse(surveyId);

		List<FeedbackCreateRequest> feedbackCreateRequestList = List.of(
			getFeedbackCreateRequest(surveyFindResponse, true, "developer"),
			getFeedbackCreateRequest(surveyFindResponse, false, "developer"));
		createFeedback(surveyId, OBJECT_MAPPER.writeValueAsString(feedbackCreateRequestList.get(0)));
		createFeedback(surveyId, OBJECT_MAPPER.writeValueAsString(feedbackCreateRequestList.get(1)));

		// when
		ResultActions resultActions = summarizeReviewer(token, surveyId);

		// then
		assertIsReviewerSummarized(resultActions);
	}

	private String saveTargetAndGetToken(String name) {
		Long targetId = targetInitializer.saveTargetAndGetId("nalab", Instant.now());
		String token = "nalab-token";
		applicationEventPublisher.publishEvent(MockUserRegisterEvent.builder()
			.expectedToken(token)
			.expectedId(targetId)
			.build());
		return token;
	}

	private Long saveAndGetSurveyId(String token, String content) throws Exception {
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
			.type("choice")
			.questionId(choiceFormQuestionResponse.getQuestionId())
			.choiceList(List.of(choiceFormQuestionResponse.getChoices().get(0).getChoiceId()))
			.build();
	}

}
