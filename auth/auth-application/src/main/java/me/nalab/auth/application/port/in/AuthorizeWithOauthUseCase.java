package me.nalab.auth.application.port.in;

import java.net.URI;

/**
 * OAuth Provider의 인증 허가(authorize) 처리합니다
 */
public interface AuthorizeWithOauthUseCase {

    /**
     * 인증 허가를 위한 uri을 가져옵니다
     * @param providerName provider 이름
     * @return 인증 허가 uri
     */
    URI getAuthorizationUrl(String providerName);
}
