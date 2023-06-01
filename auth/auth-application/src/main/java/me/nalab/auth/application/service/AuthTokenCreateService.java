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

@Service
@RequiredArgsConstructor
public class AuthTokenCreateService implements AuthTokenCreateUseCase {

	private final JwtUtils jwtUtils;

	@Override
	public AuthToken create(CreateAuthTokenRequest request) {
		var userId = request.getUserId();
		var nickname = request.getNickname();
		if (userId.isBlank() || nickname.isBlank()) throw new IllegalArgumentException();

		Set<Payload> payload = new HashSet<>();
		payload.add(new Payload(Payload.Key.USER_ID, userId));
		payload.add(new Payload(Payload.Key.NICKNAME, nickname));

		String token = createToken(payload);
		return new AuthToken(token);
	}

	private String createToken(Set<Payload> payload) {
		return jwtUtils.createAccessToken(payload);
	}
}
