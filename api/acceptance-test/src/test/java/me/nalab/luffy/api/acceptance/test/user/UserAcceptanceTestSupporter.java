package me.nalab.luffy.api.acceptance.test.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import me.nalab.luffy.api.acceptance.test.DatabaseCleaner;

public class UserAcceptanceTestSupporter {

	private MockMvc mockMvc;
	private DatabaseCleaner databaseCleaner;
	private static final String API_VERSION = "/v1";

	protected ResultActions getLoginedUser(String token) throws Exception {
		return mockMvc.perform(MockMvcRequestBuilders
			.get(API_VERSION + "/users/logins")
			.accept(MediaType.APPLICATION_JSON)
			.header(HttpHeaders.AUTHORIZATION, token)
		);
	}

	protected ResultActions updateTargetPosition(String token, String content) throws Exception {
		return mockMvc.perform(MockMvcRequestBuilders
			.patch(API_VERSION + "/users")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON)
			.header(HttpHeaders.AUTHORIZATION, token)
			.content(content)
		);
	}

	@Autowired
	final void setMockMvc(MockMvc mockMvc) {
		this.mockMvc = mockMvc;
	}

}
