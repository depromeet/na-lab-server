package me.nalab.api.acceptance.survey;

import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

class SurveyAcceptanceTestValidator {

	void expectedIsCreated(ResultActions result) throws Exception {
		result.andExpectAll(
			MockMvcResultMatchers.status().isCreated(),
			MockMvcResultMatchers.header().string(HttpHeaders.CONTENT_TYPE, "application/json"),
			MockMvcResultMatchers.jsonPath("$.survey_id").isString()
		);
	}

}
