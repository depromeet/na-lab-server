package me.nalab.auth.application.port.in.web;

import me.nalab.auth.application.common.dto.OAuthResourceRequest;
import me.nalab.auth.application.common.dto.OAuthResourceResponse;

/**
 * OAuth Provider의 Resource 서버 접근해 필요한 정보를 가져옵니다.
 */
public interface OAuthGetResourceUseCase {

    /**
     * 리소스 서버에서 필요한 유저의 데이터를 가져옵니다.
     * @param request 리소스 서버 접근을 하기 위한 auth token 정보
     * @return 유저의 이메일과 닉네임 데이터
     */
    OAuthResourceResponse get(OAuthResourceRequest request);
}
