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

	protected ResultActions postSignInWithOAuth(String oauthProvider, String content) throws Exception {
		var path = "/v1/oauth/" + oauthProvider;
		var mockMvcRequest = MockMvcRequestBuilders.post(path)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(content);
		return mockMvc.perform(mockMvcRequest);
	}

	protected ResultActions getAuthorizeByOAuthProvider(String oauthProvider) throws Exception {
		var path = "/v1/auth/oauth/" + oauthProvider;
		var mockMvcRequest = MockMvcRequestBuilders.get(path).accept(MediaType.ALL);

		return mockMvc.perform(mockMvcRequest);
	}
}
