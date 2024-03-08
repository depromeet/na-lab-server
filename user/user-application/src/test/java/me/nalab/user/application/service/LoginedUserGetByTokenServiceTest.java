package me.nalab.user.application.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import me.nalab.user.application.common.dto.LoginedInfo;
import me.nalab.user.application.exception.InvalidTokenException;
import me.nalab.user.application.port.in.LoginedUserGetByTokenUseCase;
import me.nalab.user.application.port.out.persistence.LoginedUserGetByTokenPort;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {LoginedUserGetByTokenService.class})
class LoginedUserGetByTokenServiceTest {

	@Autowired
	private LoginedUserGetByTokenUseCase loginedUserGetByTokenUseCase;

	@MockBean
	private LoginedUserGetByTokenPort loginedUserGetByTokenPort;

	@Test
	@DisplayName("토큰을 이용해 로그인된 유저의 정보를 조회 성공 테스트")
	void GET_LOGINED_INFO_BY_TOKEN_SUCCESS() {
		// given
		LoginedInfo expected = new LoginedInfo("hello", 12345L, 54321L, "email");
		String token = "hello token";

		Mockito.when(loginedUserGetByTokenPort.decryptToken(token.split(" ")[1])).thenReturn(expected);

		// when
		LoginedInfo result = loginedUserGetByTokenUseCase.decryptToken(token);

		// then
		Assertions.assertThat(result).isEqualTo(expected);
	}

	@ParameterizedTest
	@NullSource
	void NULL_PARAMETER_TEST(String token) {
		// when
		Throwable result = Assertions.catchThrowable(() -> loginedUserGetByTokenUseCase.decryptToken(token));

		// then
		Assertions.assertThat(result).isInstanceOf(NullPointerException.class);
	}

	@Test
	@DisplayName("Invalid token signature 테스트")
	void DECRYPT_INVALID_TOKEN() {
		// given
		String token = "invalid";

		// when
		Throwable result = Assertions.catchThrowable(() -> loginedUserGetByTokenUseCase.decryptToken(token));

		// then
		Assertions.assertThat(result).isInstanceOf(InvalidTokenException.class);
	}

}
