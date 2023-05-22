package me.nalab.core.exception.handler;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest
@ContextConfiguration(classes = {TestController.class, ValidationControllerAdvice.class})
class ValidationControllerAdviceTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@BeforeEach
	private void setObjectMapper(){
		objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
	}

	@Test
	@DisplayName("Validation controller advice 예외 핸들링 테스트 - length overflow")
	void VALID_OVERFLOW() throws Exception {
		// given
		TestRequest testRequest = new TestRequest("abcdasd");

		// when
		ResultActions resultActions = callHello(testRequest);

		// then
		assertIsOverFlow(resultActions);
	}

	@Test
	@DisplayName("Validation controller advice 예외 핸들링 테스트 - null")
	void VALID_NULL() throws Exception {
		// given
		TestRequest testRequest = new TestRequest(null);

		// when
		ResultActions resultActions = callHello(testRequest);

		// then
		assertIsNull(resultActions);
	}

	@Test
	@DisplayName("Validation controller advice 예외 핸들링 테스트 - blank")
	void VALID_BLANK() throws Exception{
		// given
		TestRequest testRequest = new TestRequest("   ");

		// when
		ResultActions resultActions = callHello(testRequest);

		// then
		assertIsBlank(resultActions);
	}

	private ResultActions callHello(TestRequest request) throws Exception {
		return mockMvc.perform(MockMvcRequestBuilders.post("/hello")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(request))
		);
	}

	private void assertIsOverFlow(ResultActions resultActions) throws Exception {
		resultActions.andExpectAll(
			status().isBadRequest(),
			jsonPath("$.response_messages").value("overflow")
		);
	}

	private void assertIsNull(ResultActions resultActions) throws Exception {
		resultActions.andExpectAll(
			status().isBadRequest(),
			jsonPath("$.response_messages").value("null")
		);
	}

	private void assertIsBlank(ResultActions resultActions) throws Exception {
		resultActions.andExpectAll(
			status().isBadRequest(),
			jsonPath("$.response_messages").value("blank")
		);
	}

}
