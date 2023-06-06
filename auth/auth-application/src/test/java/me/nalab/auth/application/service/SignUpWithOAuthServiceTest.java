package me.nalab.auth.application.service;

import me.nalab.auth.application.common.dto.SignUpWithOAuthRequest;
import me.nalab.user.application.port.in.UserCreateWithOAuthUseCase;
import me.nalab.user.application.port.in.UserFindByProviderAndTokenUseCase;
import me.nalab.user.domain.user.Provider;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SignUpWithOAuthService.class})
class SignUpWithOAuthServiceTest {

    @Autowired
    private SignUpWithOAuthService signUpWithOAuthService;

    @MockBean
    private UserFindByProviderAndTokenUseCase userFindByProviderAndTokenUseCase;

    @MockBean
    private UserCreateWithOAuthUseCase userCreateWithOAuthUseCase;


    @Test
    @DisplayName("provider가 null이면 예외를 던진다")
    void THROW_EXCEPTION_WHEN_PROVIDER_IS_NULL() {
        // given
        String providerName = null;
        var request = new SignUpWithOAuthRequest(providerName, "test@test.com", "username", null);

        // when
        var throwable = Assertions.catchThrowable(() -> signUpWithOAuthService.signUpWithOAuth(request));

        // then
        Assertions.assertThat(throwable).isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("email가 null이면 예외를 던진다")
    void THROW_EXCEPTION_WHEN_EMAIL_IS_NULL() {
        // given
        String email = null;
        var request = new SignUpWithOAuthRequest(Provider.KAKAO.name(), email, "username", null);

        // when
        var throwable = Assertions.catchThrowable(() -> signUpWithOAuthService.signUpWithOAuth(request));

        // then
        Assertions.assertThat(throwable).isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("이미 해당 provider와 email로 된 사용자가 있다면 정상 통과한다")
    void RETURN_VOID_WHEN_EXIST_USER_BY_PROVIDER_AND_EMAIL() {
        // given
        var provider = Provider.KAKAO;
        var email = "test@test.com";
        var request = new SignUpWithOAuthRequest(provider.name(), email, "username", null);
        when(userFindByProviderAndTokenUseCase.findByProviderAndToken(any())).thenReturn(Optional.of(1L));

        // when
        var throwable = Assertions.catchThrowable(() -> signUpWithOAuthService.signUpWithOAuth(request));

        // then
        Assertions.assertThat(throwable).isNull();
    }

    @Test
    @DisplayName("providerName에 해당하는 provider가 없다면 예외를 던진다")
    void THROW_EXCEPTION_WHEN_NOT_MATCHED_PROVIDER_NAME() {
        // given
        var providerName = "not found provider name";
        var email = "test@test.com";
        var request = new SignUpWithOAuthRequest(providerName, email, "username", null);
        when(userFindByProviderAndTokenUseCase.findByProviderAndToken(any())).thenReturn(Optional.empty());

        // when
        var throwable = Assertions.catchThrowable(() -> signUpWithOAuthService.signUpWithOAuth(request));

        // then
        Assertions.assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("정상적인 인자라면 정상 통과한다")
    void RETURN_VOID_WHEN_VALID_INPUT() {
        // given
        var provider = Provider.KAKAO;
        var email = "test@test.com";
        var request = new SignUpWithOAuthRequest(provider.name(), email, "username", null);
        when(userFindByProviderAndTokenUseCase.findByProviderAndToken(any())).thenReturn(Optional.empty());

        // when
        var throwable = Assertions.catchThrowable(() -> signUpWithOAuthService.signUpWithOAuth(request));

        // then
        Assertions.assertThat(throwable).isNull();
    }

}
