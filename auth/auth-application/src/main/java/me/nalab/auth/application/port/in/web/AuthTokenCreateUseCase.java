package me.nalab.auth.application.port.in.web;

import me.nalab.auth.application.common.dto.AuthToken;
import me.nalab.auth.application.common.dto.CreateAuthTokenRequest;

/**
 * Sign In 시에 인증용 토큰을 생성합니다.
 */
public interface AuthTokenCreateUseCase {

	/**
	 * auth 토큰 생성 요청
	 * @param createAuthTokenRequest 토큰생성에 필요한 정보를 포함한 dto
	 * @return AuthToken token 정보를 포함한 dto
	 */
	AuthToken create(CreateAuthTokenRequest createAuthTokenRequest);
}
