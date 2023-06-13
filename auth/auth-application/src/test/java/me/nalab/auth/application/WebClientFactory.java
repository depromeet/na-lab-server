package me.nalab.auth.application;

import me.nalab.auth.application.common.dto.OAuthAccessTokenResponse;
import me.nalab.auth.application.port.out.OAuthWebClientPort;

import java.net.URI;

public class WebClientFactory {
    public static OAuthWebClientPort createFakeWebClient(OAuthAccessTokenResponse accessTokenResponse) {
        return new OAuthWebClientPort() {
            @Override
            public OAuthAccessTokenResponse getAccessToken(String authToken) {
                return accessTokenResponse;
            }

            @Override
            public URI getAuthorizationUrl(String redirectUrl) {
                return URI.create("test.com/path?redirect-url="+redirectUrl);
            }
        };
    }

}
