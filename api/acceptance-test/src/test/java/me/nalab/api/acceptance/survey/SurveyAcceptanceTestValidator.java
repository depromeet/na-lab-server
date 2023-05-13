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

	void expectedIsTokenExpired(ResultActions result) throws Exception{
		result.andExpectAll(
			MockMvcResultMatchers.status().isUnauthorized(),
			MockMvcResultMatchers.header().string(HttpHeaders.CONTENT_TYPE, "application/json"),
			MockMvcResultMatchers.jsonPath("$.response_messages").value("Expired token")
		);
	}

	void expectedIsTokenInvalid(ResultActions result) throws Exception{
		result.andExpectAll(
			MockMvcResultMatchers.status().isUnauthorized(),
			MockMvcResultMatchers.header().string(HttpHeaders.CONTENT_TYPE, "application/json"),
			MockMvcResultMatchers.jsonPath("$.response_messages").value("Invalid token")
		);
	}

}
