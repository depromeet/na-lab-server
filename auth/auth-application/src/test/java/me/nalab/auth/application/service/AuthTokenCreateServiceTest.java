package me.nalab.auth.application.service;

import me.nalab.auth.application.common.dto.CreateAuthTokenRequest;
import me.nalab.auth.application.common.utils.JwtUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AuthTokenCreateService.class})
class AuthTokenCreateServiceTest {

	@Autowired
	private AuthTokenCreateService authTokenCreateService;

	@MockBean
	private JwtUtils jwtUtils;

	@ParameterizedTest
	@NullAndEmptySource()
	@DisplayName("유저식별자가 없거나 비어있다면 예외를 발생시킨다")
	void THROW_EXCEPTION_WHEN_USER_ID_IS_BLANK(String userId) {
		// given
		var nickname = "nickname";
		var targetId = "targetId";
		var request = new CreateAuthTokenRequest(userId, nickname, targetId);

		// when
		var throwable = Assertions.catchThrowable(() -> authTokenCreateService.create(request));

		// then
		Assertions.assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
	}

	@ParameterizedTest
	@NullAndEmptySource()
	@DisplayName("닉네임가 없거나 비어있다면 예외를 발생시킨다")
	void THROW_EXCEPTION_WHEN_NICKNAME_IS_BLANK(String nickname) {
		// given
		var userId = "userId";
		var targetId = "targetId";
		var request = new CreateAuthTokenRequest(userId, nickname, targetId);

		// when
		var throwable = Assertions.catchThrowable(() -> authTokenCreateService.create(request));

		// then
		Assertions.assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
	}

	@ParameterizedTest
	@NullAndEmptySource()
	@DisplayName("타겟 아이디가 없거나 비어있다면 예외를 발생시킨다")
	void THROW_EXCEPTION_WHEN_TARGET_ID_IS_BLANK(String targetId) {
		// given
		var userId = "userId";
		var nickname = "nickname";
		var request = new CreateAuthTokenRequest(userId, nickname, targetId);

		// when
		var throwable = Assertions.catchThrowable(() -> authTokenCreateService.create(request));

		// then
		Assertions.assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("정상적인 요청이 들어오면 토큰을 생성한다")
	void RETURN_TOKEN_WHEN_VALID_REQUEST() {
		// given
		var userId = "userId";
		var nickname = "nickname";
		var targetId = "targetId";
		var request = new CreateAuthTokenRequest(userId, nickname, targetId);
		var expectedToken = "token";

		when(jwtUtils.createAccessToken(any())).thenReturn(expectedToken);

		// when
		var authToken = authTokenCreateService.create(request);

		// then
		Assertions.assertThat(authToken).isNotNull();
		Assertions.assertThat(authToken.getToken()).isNotBlank().isEqualTo(expectedToken);
	}


}
