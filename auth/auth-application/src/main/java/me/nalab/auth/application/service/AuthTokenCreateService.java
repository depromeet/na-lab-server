package me.nalab.auth.application.service;

import java.util.HashSet;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import me.nalab.auth.application.common.dto.AuthToken;
import me.nalab.auth.application.common.dto.CreateAuthTokenRequest;
import me.nalab.auth.application.common.dto.Payload;
import me.nalab.auth.application.port.in.web.AuthTokenCreateUseCase;

@Service
@RequiredArgsConstructor
public class AuthTokenCreateService implements AuthTokenCreateUseCase {

	private final JwtService jwtService;

	@Override
	public AuthToken create(CreateAuthTokenRequest request) {
		var payload = new HashSet<Payload>();
		payload.add(new Payload(Payload.Key.USER_ID, request.getUserId()));
		payload.add(new Payload(Payload.Key.NICKNAME, request.getNickname()));

		var jwt = jwtService.createAccessToken(payload);
		return new AuthToken(jwt);
	}
}
