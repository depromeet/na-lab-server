package me.nalab.auth.mock.interceptor;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

abstract class AbstractMockAuthTest {

	@Autowired
	MockMvc mvc;

	Long call(HttpMethod httpMethod, String url, String token) throws Exception {
		if(httpMethod == HttpMethod.GET) {
			return get(url, token);
		}
		if(httpMethod == HttpMethod.POST) {
			return post(url, token);
		}
		throw new IllegalStateException("Cannot find correct test method for \"" + httpMethod.name() + "\"");
	}

	private Long get(String url, String token) throws Exception {
		ResultActions resultActions = mvc.perform(MockMvcRequestBuilders
			.get(url)
			.header("Authorization", token)
			.accept(MediaType.ALL)
		);

		return extractLoginId(resultActions);
	}

	private Long post(String url, String token) throws Exception {
		ResultActions resultActions = mvc.perform(MockMvcRequestBuilders
			.post(url)
			.header("Authorization", token)
			.contentType(MediaType.APPLICATION_JSON)
			.content("{\"mock\" : 1}")
			.accept(MediaType.ALL)
		);

		return extractLoginId(resultActions);
	}

	private Long extractLoginId(ResultActions resultActions) throws UnsupportedEncodingException {
		return Long.valueOf(resultActions.andReturn().getResponse().getContentAsString());
	}

}
