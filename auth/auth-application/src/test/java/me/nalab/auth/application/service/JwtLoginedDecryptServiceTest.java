package me.nalab.auth.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import me.nalab.auth.application.common.dto.Payload;
import me.nalab.auth.application.common.property.JwtProperties;
import me.nalab.auth.application.common.utils.JwtUtils;
import me.nalab.user.application.common.dto.TokenInfo;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {JwtLoginedDecryptService.class, JwtUtils.class, JwtProperties.class})
class JwtLoginedDecryptServiceTest {

	@Autowired
	private JwtLoginedDecryptService jwtLoginedDecryptService;

	@Autowired
	private JwtUtils jwtUtils;

	@Test
	@DisplayName("JWT 복호화 성공 테스트")
	void JWT_DECRYPT_SUCCESS() {
		// given
		String nickName = "hello";
		long userId = 12345;
		long targetId = 54321;
		String requestToken = jwtUtils.createAccessToken(
			Set.of(new Payload(Payload.Key.NICKNAME, nickName), new Payload(Payload.Key.USER_ID, userId + ""),
				new Payload(Payload.Key.TARGET_ID, targetId + "")));

		// when
		TokenInfo response = jwtLoginedDecryptService.decryptToken(requestToken);

		// then
		assertDecryptedInfo(response, nickName, userId, targetId);
	}

	@ParameterizedTest
	@NullAndEmptySource
	@DisplayName("token이 null이거나 empty면 예외를 던진다.")
	void THROW_EXCEPTION_WHEN_TOKEN_IS_NULL_OR_EMPTY(String token) {
		// when
		Throwable throwable = catchThrowable(() -> jwtLoginedDecryptService.decryptToken(token));

		// then
		assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
	}

	private void assertDecryptedInfo(TokenInfo response, String expectedName, Long expectedUserId,
		Long expectedTargetId) {
		Assertions.assertEquals(response.getNickName(), expectedName);
		Assertions.assertEquals(response.getUserId(), expectedUserId);
		Assertions.assertEquals(response.getTargetId(), expectedTargetId);
	}

	@Test
	@DisplayName("Target ID 반환 성공 테스트")
	void DECRYPTE_TARGET_ID_SUCCESS() {
		// given
		String nickName = "hello";
		long userId = 12345;
		long targetId = 54321;
		String requestToken = jwtUtils.createAccessToken(Set.of(
				new Payload(Payload.Key.NICKNAME, nickName), 
				new Payload(Payload.Key.USER_ID, String.valueOf(userId)),
				new Payload(Payload.Key.TARGET_ID, String.valueOf(targetId))
		));
		// when
		Long result = jwtLoginedDecryptService.getTargetId(requestToken);

		// then
		Assertions.assertEquals(targetId, result);
	}

}
