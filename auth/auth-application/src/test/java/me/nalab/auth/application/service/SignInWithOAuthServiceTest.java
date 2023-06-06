package me.nalab.auth.application.service;

import me.nalab.auth.application.common.dto.AuthToken;
import me.nalab.auth.application.common.dto.SignInWithOAuthRequest;
import me.nalab.auth.application.port.in.web.AuthTokenCreateUseCase;
import me.nalab.auth.application.port.in.web.SignUpWithOAuthUseCase;
import me.nalab.user.application.port.in.UserFindByProviderAndTokenUseCase;
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

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SignInWithOAuthService.class})
class SignInWithOAuthServiceTest {

    @Autowired
    private SignInWithOAuthService signInWithOAuthService;

    @MockBean
    private UserFindByProviderAndTokenUseCase userFindByProviderAndTokenUseCase;

    @MockBean
    private SignUpWithOAuthUseCase signUpWithOAuthUseCase;

    @MockBean
    private AuthTokenCreateUseCase authTokenCreateUseCase;

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("provider가 null이나 빈 값이면 예외를 던진다")
    void THROW_EXCEPTION_WHEN_PROVIDER_IS_NULL_OR_EMPTY(String providerName) {
        // given
        var request = createRequest().providerName(providerName).build();

        // when
        var throwable = Assertions.catchThrowable(() -> signInWithOAuthService.signInWithOAuth(request));

        // then
        Assertions.assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("email가 null이나 빈 값이면 예외를 던진다")
    void THROW_EXCEPTION_WHEN_EMAIL_IS_NULL_OR_EMPTY(String email) {
        // given
        var request = createRequest().email(email).build();

        // when
        var throwable = Assertions.catchThrowable(() -> signInWithOAuthService.signInWithOAuth(request));

        // then
        Assertions.assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("정상적인 인자가 들어오면 Auth token을 반환한다")
    void RETURN_AUTH_TOKEN_WHEN_VALID_INPUT() {
        // given
        var expectedToken = new AuthToken("token");
        when(userFindByProviderAndTokenUseCase.findByProviderAndToken(any())).thenReturn(Optional.of(1L));
        when(authTokenCreateUseCase.create(any())).thenReturn(expectedToken);
        var request = createRequest().build();

        // when
        var authToken = signInWithOAuthService.signInWithOAuth(request);

        // then
        Assertions.assertThat(authToken).isEqualTo(expectedToken);
    }



    private SignInWithOAuthRequest.SignInWithOAuthRequestBuilder createRequest() {
        return SignInWithOAuthRequest.builder()
                .providerName(Provider.KAKAO.name())
                .username("username")
                .email("test@test.com")
                .phoneNumber(null);
    }
}
