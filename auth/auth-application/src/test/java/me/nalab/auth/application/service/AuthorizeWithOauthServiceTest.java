package me.nalab.auth.application.service;

import me.nalab.auth.application.WebClientFactory;
import me.nalab.auth.application.port.out.OAuthWebClientFactory;
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
@ContextConfiguration(classes = {AuthorizeWithOauthService.class})
class AuthorizeWithOauthServiceTest {

    @Autowired
    private AuthorizeWithOauthService authorizeWithOauthService;

    @MockBean
    private OAuthWebClientFactory oauthWebClientFactory;


    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("provider name이 없다면 예외를 던진다")
    void THROW_EXCEPTION_NOT_FOUND_PROVIDER_BY_NAME(String providerName) {
        // given
        // when
        var throwable = Assertions.catchThrowable(() -> authorizeWithOauthService.getAuthorizationUrl(providerName));

        // then
        Assertions.assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("정상적인 값이라면 redirectURl을 반환한다")
    void RETURN_REDIRECT_URI_WHEN_VALID_INPUT() {
        // given
        var provider = Provider.KAKAO;
        var fakeWebClient = WebClientFactory.createFakeWebClient(null, null);
        when(oauthWebClientFactory.getClient(provider)).thenReturn(fakeWebClient);

        // when
        var result = authorizeWithOauthService.getAuthorizationUrl(provider.name());

        // then
        Assertions.assertThat(result).isNotNull();
    }


}
