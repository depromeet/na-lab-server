package me.nalab.auth.application.service;

import me.nalab.auth.application.port.in.AuthorizeWithOauthUseCase;
import me.nalab.auth.application.port.out.OAuthWebClientFactory;
import me.nalab.user.domain.user.Provider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.net.URI;


@Service
public class AuthorizeWithOauthService implements AuthorizeWithOauthUseCase {

    private final OAuthWebClientFactory oAuthWebClientFactory;

    private final String baseUrl;

    public AuthorizeWithOauthService(
            OAuthWebClientFactory oAuthWebClientFactory,
            @Value("${luffy.base-url:https://api.nalab.me}") String baseUrl
    ) {
        this.oAuthWebClientFactory = oAuthWebClientFactory;
        this.baseUrl = baseUrl;
    }

    @Override
    public URI getAuthorizationUrl(String providerName) {
        Assert.hasText(providerName, "provider name은 필수값 입니다.");

        var provider = Provider.valueOf(providerName.toUpperCase());
        var oauthWebClient = oAuthWebClientFactory.getClient(provider);

        var redirectUrl = baseUrl + "/v1/oauth/" + provider.name();
        return oauthWebClient.getAuthorizationUrl(redirectUrl);
    }
}
