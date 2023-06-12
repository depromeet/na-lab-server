package me.nalab.auth.application.port.out;

import me.nalab.user.domain.user.Provider;

/**
 * OAuth Provider에 맞는 WebClient 가져오는 Factory
 */
public interface OAuthWebClientFactory {

    /**
     * Provider에 맞는 WebClientPort를 가져옵니다.
     * @param provider oauth provider
     * @return OAuthProvider 접근용 WebClientPort
     *
     * @exception IllegalArgumentException 만약 대응되는 web client port가 없다면 IllegalArgumentsException을 던집니다.
     */
    OAuthWebClientPort getClient(Provider provider);
}
