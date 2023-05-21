package me.nalab.luffy.api.acceptance.test.survey;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

public class SurveyAcceptanceValidator {

	public static void assertIsSurveyCreated(ResultActions resultActions) throws Exception {
		resultActions.andExpectAll(
			status().isCreated(),
			content().contentType(MediaType.APPLICATION_JSON),
			jsonPath("$.survey_id").isNumber()
		);
	}

	public static void assertIsSurveyFound(ResultActions resultActions) throws Exception {
		resultActions.andExpectAll(
			status().isOk(),
			content().contentType(MediaType.APPLICATION_JSON),
			jsonPath("$.survey_id").isNumber()
		);
	}

	public static void assertIsTargetDoesNotHasAnySurvey(ResultActions resultActions) throws Exception {
		resultActions.andExpectAll(
			status().isNotFound(),
			content().contentType(MediaType.APPLICATION_JSON),
			jsonPath("$.response_messages").isString()
		);
	}

}
