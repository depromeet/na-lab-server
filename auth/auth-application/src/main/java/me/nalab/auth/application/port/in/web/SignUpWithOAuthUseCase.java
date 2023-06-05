package me.nalab.auth.application.port.in.web;

import me.nalab.auth.application.common.dto.SignUpWithOAuthRequest;

/**
 * OAuth 정보를 통해 Sign Up을 진행
 */
public interface SignUpWithOAuthUseCase {

    /**
     * OAuth를 통해 회원가입을 시도합니다.
     * @param request OAuth 정보를 포함하는 dto
     */
    void signUpWithOAuth(SignUpWithOAuthRequest request);
}
