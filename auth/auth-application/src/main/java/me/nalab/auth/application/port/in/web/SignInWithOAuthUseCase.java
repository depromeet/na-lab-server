package me.nalab.auth.application.port.in.web;

import me.nalab.auth.application.common.dto.AuthToken;
import me.nalab.auth.application.common.dto.SignInWithOAuthRequest;

/**
 * OAuth 정보를 통해 Sign In을 진행
 */
public interface SignInWithOAuthUseCase {

    /**
     * OAuth를 통해 로그인을 시도합니다.
     *
     * @param request OAuth 정보를 포함하는 dto
     * @return Authentication 정보가 담긴 token
     */
    AuthToken signInWithOAuth(SignInWithOAuthRequest request);
}

