package me.nalab.auth.application.service;

import me.nalab.auth.application.common.dto.SignUpWithOAuthRequest;
import me.nalab.user.application.port.in.UserCreateWithOAuthUseCase;
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
@ContextConfiguration(classes = {SignUpWithOAuthService.class})
class SignUpWithOAuthServiceTest {

    @Autowired
    private SignUpWithOAuthService signUpWithOAuthService;

    @MockBean
    private UserFindByProviderAndTokenUseCase userFindByProviderAndTokenUseCase;

    @MockBean
    private UserCreateWithOAuthUseCase userCreateWithOAuthUseCase;


    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("provider가 null이면 예외를 던진다")
    void THROW_EXCEPTION_WHEN_PROVIDER_IS_NULL(String providerName) {
        // given
        var request = createRequest().providerName(providerName).build();

        // when
        var throwable = Assertions.catchThrowable(() -> signUpWithOAuthService.signUpWithOAuth(request));

        // then
        Assertions.assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("email가 null이면 예외를 던진다")
    void THROW_EXCEPTION_WHEN_EMAIL_IS_NULL(String email) {
        // given
        var request = createRequest().email(email).build();

        // when
        var throwable = Assertions.catchThrowable(() -> signUpWithOAuthService.signUpWithOAuth(request));

        // then
        Assertions.assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("이미 해당 provider와 email로 된 사용자가 있다면 정상 통과한다")
    void RETURN_VOID_WHEN_EXIST_USER_BY_PROVIDER_AND_EMAIL() {
        // given
        var provider = Provider.KAKAO;
        var email = "test@test.com";
        var request = createRequest().providerName(provider.name()).email(email).build();
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
        var request = createRequest().providerName(providerName).email(email).build();
        when(userFindByProviderAndTokenUseCase.findByProviderAndToken(any())).thenReturn(Optional.empty());

        // when
        var throwable = Assertions.catchThrowable(() -> signUpWithOAuthService.signUpWithOAuth(request));

        // then
        Assertions.assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("정상적인 인자라면 생성된 유저의 식별자를 반환한다")
    void RETURN_NEW_USER_ID_WHEN_VALID_INPUT() {
        // given
        var provider = Provider.KAKAO;
        var email = "test@test.com";
        var request = createRequest().providerName(provider.name()).email(email).build();
        when(userFindByProviderAndTokenUseCase.findByProviderAndToken(any())).thenReturn(Optional.empty());

        long createdUserId = 1L;
        when(userCreateWithOAuthUseCase.createUser(any())).thenReturn(createdUserId);

        // when
        var userId = signUpWithOAuthService.signUpWithOAuth(request);

        // then
        Assertions.assertThat(userId).isEqualTo(createdUserId);
    }

    private SignUpWithOAuthRequest.SignUpWithOAuthRequestBuilder createRequest() {
        return SignUpWithOAuthRequest.builder()
                .providerName(Provider.KAKAO.name())
                .email("test@test.com")
                .username("username")
                .phoneNumber(null);
    }

}
