package me.nalab.luffy.api.acceptance.test.auth.signin;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.nalab.auth.application.common.dto.OAuthAccessTokenResponse;
import me.nalab.auth.application.common.dto.OAuthResourceResponse;
import me.nalab.auth.application.port.in.web.OAuthGetAccessTokenUseCase;
import me.nalab.auth.application.port.in.web.OAuthGetResourceUseCase;
import me.nalab.luffy.api.acceptance.test.TargetInitializer;
import me.nalab.luffy.api.acceptance.test.UserInitializer;
import me.nalab.user.domain.user.Provider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.ResultActions;

import javax.transaction.Transactional;
import java.time.Instant;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("classpath:h2.properties")
@ComponentScan("me.nalab")
@EnableJpaRepositories(basePackages = {"me.nalab"})
@EntityScan(basePackages = {"me.nalab"})
class SignInWithOAuthAcceptanceTest extends AbstractAuthTestSupporter {

	@MockBean
	private OAuthGetAccessTokenUseCase authGetAccessTokenUseCase;

	@MockBean
	private OAuthGetResourceUseCase authGetResourceUseCase;

	@Autowired
	private UserInitializer userInitializer;

	@Autowired
	private TargetInitializer targetInitializer;

	private static final ObjectMapper OBJECT_MAPPER;
	private static final String DEFAULT_TOKEN = "token";
	private static final String DEFAULT_TOKEN_TYPE = "bearer";
	private static final String DEFAULT_EMAIL = "test@test.com";
	private static final String DEFAULT_NAME = "name";

	static {
		OBJECT_MAPPER = new ObjectMapper();
		OBJECT_MAPPER.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
	}

	@BeforeEach
	void mocking() {
		var accessTokenResponse = new OAuthAccessTokenResponse(DEFAULT_TOKEN, DEFAULT_TOKEN_TYPE, null);
		when(authGetAccessTokenUseCase.get(any())).thenReturn(accessTokenResponse);

		var resourceResponse = new OAuthResourceResponse(DEFAULT_EMAIL, DEFAULT_NAME);
		when(authGetResourceUseCase.get(any())).thenReturn(resourceResponse);
	}

	@Test
	@DisplayName("회원 가입 되어 있지 않은 유저에 대한 kakao oauth 기반 로그인 성공 테스트")
	@Transactional
	void SIGN_IN_WITH_KAKAO_PROVIDER_WHEN_NOT_SIGN_UP_USER_SUCCESS() throws Exception {
		// given
		var oauthProvider = Provider.KAKAO.name();
		var code = "accessCode";

		// when
		ResultActions resultActions = postSignInWithOAuth(oauthProvider, code, null, null);

		// then
		resultActions.andExpectAll(
				status().isOk(),
				jsonPath("$.token_type").isNotEmpty(),
				jsonPath("$.access_token").isNotEmpty()
		);
	}

	@Test
	@DisplayName("회원 가입된 유저에 대한 OAuth 기반 로그인 테스트")
	@Transactional
	void SIGN_IN_WITH_KAKAO_PROVIDER_WHEN_ALREADY_SIGN_UP_USER_SUCCESS() throws Exception {
		// given
		var provider = Provider.KAKAO;
		var oauthProvider = provider.name();
		var code = "accessCode";

		var now = Instant.now();
		userInitializer.saveUserWithOAuth(provider, DEFAULT_NAME, DEFAULT_EMAIL, now);
		targetInitializer.saveTargetAndGetId(DEFAULT_NAME, now);

		// when
		ResultActions resultActions = postSignInWithOAuth(oauthProvider, code, null, null);

		// then
		resultActions.andExpectAll(
				status().isOk(),
				jsonPath("$.token_type").isNotEmpty(),
				jsonPath("$.access_token").isNotEmpty()
		);
	}
}
