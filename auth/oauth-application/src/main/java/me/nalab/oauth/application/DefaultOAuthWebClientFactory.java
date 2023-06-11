package me.nalab.oauth.application;

import lombok.RequiredArgsConstructor;
import me.nalab.auth.application.port.out.OAuthWebClientFactory;
import me.nalab.auth.application.port.out.OAuthWebClientPort;
import me.nalab.oauth.application.kakao.KakaoOAuthWebClient;
import me.nalab.user.domain.user.Provider;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultOAuthWebClientFactory implements OAuthWebClientFactory {

    private KakaoOAuthWebClient kakaoOAuthWebClient;

    @Override
    public OAuthWebClientPort getClient(Provider provider) {
        if (provider == Provider.KAKAO) {
            return kakaoOAuthWebClient;
        }

        throw new IllegalArgumentException(provider.name() + "의 client가 존재하지 않습니다.");
    }

}
