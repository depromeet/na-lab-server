package me.nalab.luffy.api.acceptance.test.auth.signin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public abstract class AbstractAuthTestSupporter {

	private MockMvc mockMvc;

	@Autowired
	final void setMockMvc(MockMvc mockMvc) {
		this.mockMvc = mockMvc;
	}

	protected ResultActions postSignInWithOAuth(String oauthProvider, String code, String error, String errorDescription) throws Exception {
		var path = "/v1/oauth/" + oauthProvider;
		var mockMvcRequest = MockMvcRequestBuilders.get(path)
				.queryParam("code", code)
				.queryParam("error", error)
				.queryParam("error_description", errorDescription)
				.accept(MediaType.APPLICATION_JSON);
		return mockMvc.perform(mockMvcRequest);
	}

	protected ResultActions getAuthorizeByOAuthProvider(String oauthProvider) throws Exception {
		var path = "/v1/auth/oauth/" + oauthProvider;
		var mockMvcRequest = MockMvcRequestBuilders.get(path).accept(MediaType.ALL);

		return mockMvc.perform(mockMvcRequest);
	}
}
