package me.nalab.auth.application.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import me.nalab.auth.application.common.dto.AuthToken;
import me.nalab.auth.application.common.dto.CreateAuthTokenRequest;
import me.nalab.auth.application.common.dto.Payload;
import me.nalab.auth.application.common.utils.JwtUtils;
import me.nalab.auth.application.port.in.web.AuthTokenCreateUseCase;
import org.springframework.util.Assert;

@Service
@RequiredArgsConstructor
public class AuthTokenCreateService implements AuthTokenCreateUseCase {

	private final JwtUtils jwtUtils;

	@Override
	public AuthToken create(CreateAuthTokenRequest request) {
		var userId = request.getUserId();
		var nickname = request.getNickname();
		var targetId = request.getTargetId();
		var email = request.getEmail();
		Assert.isTrue(userId != null && !userId.isBlank(), "Authentication token 생성 시 유저 식별자는 필수입니다.");
		Assert.isTrue(nickname != null && !nickname.isBlank(), "Authentication token 생성 시 유저의 별명은 필수입니다.");
		Assert.isTrue(targetId != null && !targetId.isBlank(), "Authentication token 생성 시 유저의 별명은 필수입니다.");
		Assert.isTrue(email != null && !email.isBlank(), "Authentication token 생성 시 유저의 이메일은 필수입니다.");

		Set<Payload> payload = new HashSet<>();
		payload.add(new Payload(Payload.Key.USER_ID, userId));
		payload.add(new Payload(Payload.Key.NICKNAME, nickname));
		payload.add(new Payload(Payload.Key.TARGET_ID, targetId));
		payload.add(new Payload(Payload.Key.EMAIL, email));

		String token = createToken(payload);
		return new AuthToken(token);
	}

	private String createToken(Set<Payload> payload) {
		return jwtUtils.createAccessToken(payload);
	}
}
