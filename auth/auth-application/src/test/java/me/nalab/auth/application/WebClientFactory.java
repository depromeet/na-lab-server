package me.nalab.auth.application;

import me.nalab.auth.application.common.dto.AuthToken;
import me.nalab.auth.application.common.dto.OAuthAccessTokenResponse;
import me.nalab.auth.application.common.dto.OAuthResourceResponse;
import me.nalab.auth.application.port.out.OAuthWebClientPort;

import java.net.URI;

public class WebClientFactory {
    public static OAuthWebClientPort createFakeWebClient(OAuthAccessTokenResponse accessTokenResponse, OAuthResourceResponse oAuthResourceResponse) {
        return new OAuthWebClientPort() {

            @Override
            public OAuthResourceResponse getResource(String authToken) {
                return oAuthResourceResponse;
            }

            @Override
            public OAuthAccessTokenResponse getAccessToken(AuthToken authToken) {
                return accessTokenResponse;
            }

            @Override
            public URI getAuthorizationUrl(String redirectUrl) {
                return URI.create("test.com/path?redirect-url="+redirectUrl);
            }
        };
    }

}
