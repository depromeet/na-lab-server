package me.nalab.api.acceptance.survey;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import me.nalab.api.acceptance.survey.request.createsurvey.ChoiceQuestionRequest;
import me.nalab.api.acceptance.survey.request.createsurvey.QuestionType;
import me.nalab.api.acceptance.survey.request.createsurvey.Questionable;
import me.nalab.api.acceptance.survey.request.createsurvey.ShortQuestionRequest;
import me.nalab.api.acceptance.survey.request.createsurvey.SurveyCreateRequest;

@SpringBootTest
@AutoConfigureMockMvc
class SurveyAcceptanceTest {

	private final SurveyAcceptanceTestHelper testHelper;
	private final SurveyAcceptanceTestValidator testValidator;

	@Autowired
	SurveyAcceptanceTest(ObjectMapper objectMapper, MockMvc mockMvc) {
		objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
		this.testHelper = new SurveyAcceptanceTestHelper(mockMvc, objectMapper);
		this.testValidator = new SurveyAcceptanceTestValidator();
	}

	@Test
	@DisplayName("질문폼 생성 인수 테스트")
	void CREATE_SURVEY_FORM_SUCCESS() throws Exception{
		// given
		String token = getToken();
		SurveyCreateRequest surveyCreateRequest = getDefaultSurveyCreateRequest();

		// when
		ResultActions result = testHelper.createSurvey(token, surveyCreateRequest);

		// then
		testValidator.expectedIsCreated(result);
	}

	@Test
	@DisplayName("질문폼 생성 실패 인수테스트 - 올바르지 않은 토큰")
	void CREATE_SURVEY_FORM_FAIL_INVALID_TOKEN() throws Exception{
		// given
		String token = "invalid";
		SurveyCreateRequest surveyCreateRequest = getDefaultSurveyCreateRequest();

		// when
		ResultActions result = testHelper.createSurvey(token, surveyCreateRequest);

		// then
		testValidator.expectedIsTokenInvalid(result);
	}

	@Test
	@DisplayName("질문폼 생성 실패 인수테스트 - 만료된 토큰")
	void CREATE_SURVEY_FORM_FAIL_EXPIRED_TOKEN() throws Exception{
		// given
		String token = "expired";
		SurveyCreateRequest surveyCreateRequest = getDefaultSurveyCreateRequest();

		// when
		ResultActions result = testHelper.createSurvey(token, surveyCreateRequest);

		// then
		testValidator.expectedIsTokenInvalid(result);
	}

	private String getToken(){
		return "mock token";
	}

	private SurveyCreateRequest getDefaultSurveyCreateRequest(){
		Questionable choiceQuestionRequest = ChoiceQuestionRequest.builder()
			.title("choice")
			.order(1)
			.type(QuestionType.CHOICE.getLowerCase())
			.choices(List.of(
				ChoiceQuestionRequest.Choice.builder()
					.content("choice1")
					.order(1)
					.build(),
				ChoiceQuestionRequest.Choice.builder()
					.content("choice2")
					.order(2)
					.build()
			))
			.build();

		Questionable shortQuestionRequest = ShortQuestionRequest.builder()
			.title("short")
			.order(1)
			.type(QuestionType.SHORT.getLowerCase())
			.build();

		return SurveyCreateRequest.builder()
			.questionCount(2)
			.question(List.of(
				choiceQuestionRequest, shortQuestionRequest
			))
			.build();
	}

}
