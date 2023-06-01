package me.nalab.auth.application.port.in.web;

import me.nalab.auth.application.common.dto.AuthToken;
import me.nalab.auth.application.common.dto.CreateAuthTokenRequest;

public interface AuthTokenCreateUseCase {

	AuthToken create(CreateAuthTokenRequest createAuthTokenRequest);
}
