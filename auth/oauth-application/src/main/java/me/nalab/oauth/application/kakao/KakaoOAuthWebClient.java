package me.nalab.oauth.application.kakao;

import lombok.RequiredArgsConstructor;
import me.nalab.auth.application.common.dto.OAuthAccessTokenResponse;
import me.nalab.auth.application.port.out.OAuthWebClientPort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class KakaoOAuthWebClient implements OAuthWebClientPort {

    private final KakaoClientProperties properties;

    @Override
    public OAuthAccessTokenResponse getAccessToken(String authToken) {
        return WebClient.create()
                .post()
                .uri(properties.getAccessUrl())
                .headers(header -> {
                    header.setBasicAuth(properties.getClientId(), properties.getSecret());
                    header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                    header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
                    header.setAcceptCharset(Collections.singletonList(StandardCharsets.UTF_8));
                })
                .bodyValue(createAccessTokenRequestBody(authToken))
                .retrieve()
                .bodyToMono(OAuthAccessTokenResponse.class)
                .block();
    }

    @Override
    public URI getAuthorizationUrl(String redirectUrl) {
        var baseUrl = properties.getBaseUrl();
        var path = properties.getAuthorizePath();
        return UriComponentsBuilder.fromUriString(baseUrl)
                .path(path)
                .queryParam("client_id", properties.getClientId())
                .queryParam("redirect_url", redirectUrl)
                .queryParam("response_type", "code")
                .queryParam("scope", properties.getScope())
                .build()
                .toUri();
    }

    private Map<String, String> createAccessTokenRequestBody(String authToken) {
        Map<String, String> formData = new HashMap<>();
        formData.put("code", authToken);
        formData.put("grant_type", properties.getGrantType());
        formData.put("redirect_uri", properties.getRedirectPath());
        return formData;
    }
}
