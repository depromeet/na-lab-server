package me.nalab.oauth.application.kakao;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@Getter
@Component
@PropertySource("classpath:application.properties")
public class KakaoClientProperties {

    private final String baseUrl;
    private final String accessTokenUrl;
    private final String redirectUrl;
    private final String clientId;
    private final String secret;
    private final String grantType;

    public KakaoClientProperties(
            @Value("${oauth.kakao.base-url:https://kauth.kakao.com}") String baseUrl,
            @Value("${oauth.kakao.access-token-url:/oauth/token}") String accessTokenUrl,
            @Value("${oauth.kakao.redirect-url:/oauth/token}") String redirectUrl,
            @Value("${oauth.kakao.clientId:clientId}") String clientId,
            @Value("${oauth.kakao.secret:secret}") String secret,
            @Value("${oauth.kakao.grant-type:authorization_code}") String grantType
    ) {
        this.baseUrl = baseUrl;
        this.accessTokenUrl = accessTokenUrl;
        this.redirectUrl = redirectUrl;
        this.clientId = clientId;
        this.secret = secret;
        this.grantType = grantType;
    }

    public String getAccessUrl() {
        return baseUrl + accessTokenUrl;
    }
}

