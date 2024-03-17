package me.nalab.user.application.service;

import me.nalab.user.application.common.dto.LoginedInfo;
import me.nalab.user.application.port.out.persistence.UserGetPort;
import me.nalab.user.domain.user.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import me.nalab.user.application.common.dto.TokenInfo;
import me.nalab.user.application.exception.InvalidTokenException;
import me.nalab.user.application.port.in.LoginedUserGetByTokenUseCase;
import me.nalab.user.application.port.out.persistence.LoginedUserGetByTokenPort;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {LoginedUserGetByTokenService.class})
class LoginedUserGetByTokenServiceTest {

	private static final User DEFAULT_USER = User.builder()
		.id(54321L)
		.email("email")
		.nickname("hello")
		.build();

	@Autowired
	private LoginedUserGetByTokenUseCase loginedUserGetByTokenUseCase;

	@MockBean
	private LoginedUserGetByTokenPort loginedUserGetByTokenPort;

	@MockBean
	private UserGetPort userGetPort;

	@Test
	@DisplayName("토큰을 이용해 로그인된 유저의 정보를 조회 성공 테스트")
	void GET_LOGINED_INFO_BY_TOKEN_SUCCESS() {
		// given
		LoginedInfo expected = new LoginedInfo(DEFAULT_USER.getId(), 12345L, DEFAULT_USER.getNickname(), DEFAULT_USER.getEmail());
		TokenInfo tokenInfo = new TokenInfo(12345L, DEFAULT_USER.getId());
		String token = "hello token";

		Mockito.when(loginedUserGetByTokenPort.decryptToken(token)).thenReturn(tokenInfo);
		Mockito.when(userGetPort.getById(54321L)).thenReturn(DEFAULT_USER);

		// when
		LoginedInfo result = loginedUserGetByTokenUseCase.getLoginedInfoByToken(token);

		// then
		Assertions.assertThat(result).isEqualTo(expected);
	}

	@ParameterizedTest
	@NullSource
	void NULL_PARAMETER_TEST(String token) {
		// when
		Throwable result = Assertions.catchThrowable(() -> loginedUserGetByTokenUseCase.getLoginedInfoByToken(token));

		// then
		Assertions.assertThat(result).isInstanceOf(NullPointerException.class);
	}
}
