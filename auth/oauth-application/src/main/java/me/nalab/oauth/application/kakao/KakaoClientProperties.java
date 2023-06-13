package me.nalab.oauth.application.kakao;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Getter
@Configuration
@PropertySource("classpath:application.properties")
public class KakaoClientProperties {

    private final String baseUrl;
    private final String authorizePath;
    private final String accessTokenPath;
    private final String redirectPath;
    private final String clientId;
    private final String secret;
    private final String grantType;
    private final String scope;

    public KakaoClientProperties(
            @Value("${oauth.kakao.base-url:https://kauth.kakao.com}") String baseUrl,
            @Value("${oauth.kakao.authorize-path:/oauth/authorize}") String authorizePath,
            @Value("${oauth.kakao.access-token-path:/oauth/token}") String accessTokenPath,
            @Value("${oauth.kakao.redirect-path:/oauth/token}") String redirectPath,
            @Value("${oauth.kakao.client-id:clientId}") String clientId,
            @Value("${oauth.kakao.secret:secret}") String secret,
            @Value("${oauth.kakao.grant-type:authorization_code}") String grantType,
            @Value("${oauth.kakao.scope:profile_nickname,account_email}") String scope) {
        this.baseUrl = baseUrl;
        this.authorizePath = authorizePath;
        this.accessTokenPath = accessTokenPath;
        this.redirectPath = redirectPath;
        this.clientId = clientId;
        this.secret = secret;
        this.grantType = grantType;
        this.scope = scope;
    }

    public String getAccessUrl() {
        return baseUrl + accessTokenPath;
    }
}

