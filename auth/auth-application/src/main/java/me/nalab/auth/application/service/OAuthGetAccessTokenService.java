package me.nalab.auth.application.service;

import lombok.RequiredArgsConstructor;
import me.nalab.auth.application.common.dto.OAuthAccessTokenRequest;
import me.nalab.auth.application.common.dto.OAuthAccessTokenResponse;
import me.nalab.auth.application.port.in.web.OAuthGetAccessTokenUseCase;
import me.nalab.auth.application.port.out.OAuthWebClientFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@RequiredArgsConstructor
public class OAuthGetAccessTokenService implements OAuthGetAccessTokenUseCase {

    private final OAuthWebClientFactory oauthWebClientFactory;

    @Override
    public OAuthAccessTokenResponse get(OAuthAccessTokenRequest request) {
        checkValid(request);

        var oauthWebClientPort = oauthWebClientFactory.getClient(request.getProvider());
        return oauthWebClientPort.getAccessToken(request.getAuthToken());
    }

    private void checkValid(OAuthAccessTokenRequest request) {
        Assert.notNull(request, "access 토큰 조회를 위해서 요청 객체는 필수적입니다");
        Assert.notNull(request.getProvider(), "access 토큰 조회를 위해서 provider 정보는 필수적입니다.");
        Assert.hasText(request.getAuthToken(), "access 토큰 조회를 위해서 auth token 정보는 필수적입니다.");
    }
}
