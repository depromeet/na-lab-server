package me.nalab.core.time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import me.nalab.core.time.request.RequestTimeUtil;

@WebMvcTest
@ContextConfiguration(classes = {RequestTimeUtil.class, TestController.class})
class RequestTimeUtilTest {

	@Autowired
	private MockMvc mvc;

	@Test
	@DisplayName("요청마다 다른 Time Util 생성 테스트")
	void CREATE_TIME_UTIL() throws Exception {
		// when
		long before = getRequestArrivalTime();
		long after = getRequestArrivalTime();

		// then
		assertTrue(before < after);
	}

	private long getRequestArrivalTime() throws Exception {
		ResultActions resultActions = mvc.perform(get("/get-time")
			.accept(MediaType.APPLICATION_JSON));
		JSONObject jsonObject = new JSONObject(resultActions.andReturn().getResponse().getContentAsString());
		return jsonObject.getLong("instant");
	}

}
