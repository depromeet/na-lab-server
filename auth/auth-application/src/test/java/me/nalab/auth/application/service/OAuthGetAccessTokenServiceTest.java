package me.nalab.auth.application.service;

import me.nalab.auth.application.common.dto.OAuthAccessTokenRequest;
import me.nalab.auth.application.common.dto.OAuthAccessTokenResponse;
import me.nalab.auth.application.port.out.OAuthWebClientFactory;
import me.nalab.auth.application.port.out.OAuthWebClientPort;
import me.nalab.user.domain.user.Provider;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {OAuthGetAccessTokenService.class})
class OAuthGetAccessTokenServiceTest {

    @Autowired
    private OAuthGetAccessTokenService oauthGetAccessTokenService;

    @MockBean
    private OAuthWebClientFactory oauthWebClientFactory;

    @Test
    @DisplayName("요청 객체가 null이면 예외를 던진다")
    void THROW_EXCEPTION_WHEN_REQUEST_IS_NULL() {
        // given
        // when
        var throwable = Assertions.catchThrowable(() -> oauthGetAccessTokenService.get(null));

        // then
        Assertions.assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("요청 객체의 provider가 null이면 예외를 던진다")
    void THROW_EXCEPTION_WHEN_PROVIDER_IS_NULL() {
        // given
        var request = new OAuthAccessTokenRequest(null, "token");

        // when
        var throwable = Assertions.catchThrowable(() -> oauthGetAccessTokenService.get(request));

        // then
        Assertions.assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
    }
    
    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("요청 객체의 authToken이 null 또는 empty라면 예외를 던진다")
    void THROW_EXCEPTION_WHEN_AUTH_TOKEN_IS_NULL_OR_EMPTY(String authToken) {
        // given
        var request = new OAuthAccessTokenRequest(Provider.KAKAO, authToken);

        // when
        var throwable = Assertions.catchThrowable(() -> oauthGetAccessTokenService.get(request));

        // then
        Assertions.assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("정상적인 인자라면 access token 응답을 반환한다")
    void RETURN_ACCESS_TOKEN_RESPONSE_WHEN_VALID_INPUT() {
        // given
        var provider = Provider.KAKAO;
        var request = new OAuthAccessTokenRequest(provider, "token");

        var expectedResponse = new OAuthAccessTokenResponse("access", "tokenType", null);
        var fakeWebClient = getFakeWebClient(expectedResponse);
        when(oauthWebClientFactory.getClient(provider)).thenReturn(fakeWebClient);


        // when
        var response = oauthGetAccessTokenService.get(request);

        // then
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getToken()).isNotBlank();
        Assertions.assertThat(response.getTokenType()).isNotBlank();
    }

    private OAuthWebClientPort getFakeWebClient(OAuthAccessTokenResponse accessTokenResponse) {
        return new OAuthWebClientPort() {
            @Override
            public OAuthAccessTokenResponse getAccessToken(String authToken) {
                return accessTokenResponse;
            }
        };
    }

}
