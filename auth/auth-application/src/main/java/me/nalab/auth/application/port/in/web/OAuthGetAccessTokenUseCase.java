package me.nalab.auth.application.port.in.web;

import me.nalab.auth.application.common.dto.OAuthAccessTokenRequest;
import me.nalab.auth.application.common.dto.OAuthAccessTokenResponse;

/**
 * OAuth Provider의 Resource 서버 접근을 위한 access token 가져옵니다.
 */
public interface OAuthGetAccessTokenUseCase {
    /**
     * OAuth 제공자에 맞는 access 정보를 가져옵니다.
     *
     * @Param request access 토큰 정보 조회를 위한 provider와 auth 토큰 정보를 가진 dto
     * @return OAuth 접근위한 access 토큰 정보를 담고 있는 dto
     */
    OAuthAccessTokenResponse get(OAuthAccessTokenRequest request);
}
