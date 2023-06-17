package me.nalab.luffy.api.acceptance.test.auth.signin;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.nalab.auth.web.adaptor.api.SignInWithOAuthV1Controller;
import me.nalab.luffy.api.acceptance.test.TargetInitializer;
import me.nalab.luffy.api.acceptance.test.UserInitializer;
import me.nalab.user.domain.user.Provider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.ResultActions;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("classpath:h2.properties")
@ComponentScan("me.nalab")
@EnableJpaRepositories(basePackages = {"me.nalab"})
@EntityScan(basePackages = {"me.nalab"})
class SignInWithOAuthV1AcceptanceTest extends AbstractAuthTestSupporter {

	@Autowired
	private UserInitializer userInitializer;

	@Autowired
	private TargetInitializer targetInitializer;

	private static final ObjectMapper OBJECT_MAPPER;

	static {
		OBJECT_MAPPER = new ObjectMapper();
		OBJECT_MAPPER.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
	}

	@Test
	@DisplayName("회원 가입 되어 있지 않은 유저에 대한 kakao oauth 기반 로그인 성공 테스트")
	@Transactional
	void SIGN_IN_WITH_KAKAO_PROVIDER_WHEN_NOT_SIGN_UP_USER_SUCCESS() throws Exception {
		// given
		var email = "test@test.com";
		var nickname = "nickname";
		var apiRequest = new SignInWithOAuthV1Controller.Request(nickname, email);
		var oauthProvider = "kakao";

		// when
		ResultActions resultActions = postSignInWithOAuthV1(oauthProvider, OBJECT_MAPPER.writeValueAsString(apiRequest));

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
		var email = "test@test.com";
		var nickname = "nickname";
		var provider = Provider.KAKAO;
		var oauthProvider = provider.name();
		var apiRequest = new SignInWithOAuthV1Controller.Request(nickname, email);

		var now = LocalDateTime.now();
		userInitializer.saveUserWithOAuth(provider, nickname, email, now);
		targetInitializer.saveTargetAndGetId(nickname, now);

		// when
		ResultActions resultActions = postSignInWithOAuthV1(oauthProvider, OBJECT_MAPPER.writeValueAsString(apiRequest));

		// then
		resultActions.andExpectAll(
				status().isOk(),
				jsonPath("$.token_type").isNotEmpty(),
				jsonPath("$.access_token").isNotEmpty()
		);
	}
}
