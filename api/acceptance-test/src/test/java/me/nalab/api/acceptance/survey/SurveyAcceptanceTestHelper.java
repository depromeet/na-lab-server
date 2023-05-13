package me.nalab.api.acceptance.survey;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import me.nalab.api.acceptance.survey.request.createsurvey.SurveyCreateRequest;

class SurveyAcceptanceTestHelper {

	private final MockMvc mvc;
	private final ObjectMapper objectMapper;

	SurveyAcceptanceTestHelper(MockMvc mvc, ObjectMapper objectMapper) {
		this.mvc = mvc;
		this.objectMapper = objectMapper;
	}

	ResultActions createSurvey(String token, SurveyCreateRequest request) throws Exception {
		return mvc.perform(
			MockMvcRequestBuilders.post("/v1/surveys")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, token)
				.content(objectMapper.writeValueAsString(request))
		);
	}

}
