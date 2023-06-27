package me.nalab.auth.application.service;

import me.nalab.auth.application.WebClientFactory;
import me.nalab.auth.application.common.dto.OAuthAccessTokenResponse;
import me.nalab.auth.application.common.dto.OAuthResourceRequest;
import me.nalab.auth.application.common.dto.OAuthResourceResponse;
import me.nalab.auth.application.port.out.OAuthWebClientFactory;
import me.nalab.user.domain.user.Provider;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {OAuthGetResourceService.class})
class OAuthGetResourceServiceTest {

    @Autowired
    private OAuthGetResourceService oAuthGetResourceService;

    @MockBean
    private OAuthWebClientFactory oauthWebClientFactory;

    private static final String DEFAULT_TOKEN = "token";
    private static final String DEFAULT_TOKEN_TYPE = "bearer";

    @Test
    @DisplayName("요청 객체 속 provier name으로 provider를 못 찾으면 예외를 던진다")
    void THROW_EXCEPTION_WHEN_PROVIDER_NOT_FOUND() {
        // given
        var providerName = "not-found";
        var request = new OAuthResourceRequest(providerName, DEFAULT_TOKEN, DEFAULT_TOKEN_TYPE);

        // when
        var throwable = Assertions.catchThrowable(() -> oAuthGetResourceService.get(request));

        // then
        Assertions.assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("요청 값이 정상이라면 리소스 응답을 반환한다.")
    void RETURN_RESOURCE_WHEN_INPUT_VALID_REQUEST() {
        // given
        var email = "test@test.com";
        var name = "name";
        var providerName = Provider.KAKAO.name();
        var request = new OAuthResourceRequest(providerName, DEFAULT_TOKEN, DEFAULT_TOKEN_TYPE);

        var accessTokenResponse = new OAuthAccessTokenResponse(DEFAULT_TOKEN, DEFAULT_TOKEN_TYPE, null);
        var oAuthResourceResponse = new OAuthResourceResponse(email, name);
        var webClient = WebClientFactory.createFakeWebClient(accessTokenResponse, oAuthResourceResponse);
        when(oauthWebClientFactory.getClient(Provider.KAKAO)).thenReturn(webClient);

        // when
        var response = oAuthGetResourceService.get(request);

        // then
        Assertions.assertThat(response).isEqualTo(oAuthResourceResponse);
    }


}
