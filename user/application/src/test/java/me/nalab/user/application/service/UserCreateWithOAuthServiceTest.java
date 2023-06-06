package me.nalab.user.application.service;

import me.nalab.core.idgenerator.mock.MockIdGenerator;
import me.nalab.user.application.common.dto.CreateUserWithOAuthRequest;
import me.nalab.user.application.port.out.persistence.UserCreateWithOAuthPort;
import me.nalab.user.domain.user.Provider;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {UserCreateWithOAuthService.class})
class UserCreateWithOAuthServiceTest {

    @Autowired
    private UserCreateWithOAuthService userCreateWithOAuthService;

    @MockBean
    private UserCreateWithOAuthPort userCreateWithOAuthPort;

    @MockBean
    private MockIdGenerator mockIdGenerator;

    @Test
    @DisplayName("Provider가 null이면 예외를 던진다")
    void THROW_EXCEPTION_WHEN_PROVIDER_IS_NULL() {
        // given
        Provider provider = null;
        var email = "test@test.com";
        var username = "username";
        var request = new CreateUserWithOAuthRequest(
                provider,
                email,
                username,
                null
        );

        // when
        var throwable = Assertions.catchThrowable(() -> userCreateWithOAuthService.createUser(request));

        // then
        Assertions.assertThat(throwable).isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("email이 null이면 예외를 던진다")
    void THROW_EXCEPTION_WHEN_EMAIL_IS_NULL() {
        // given
        var provider = Provider.KAKAO;
        String email = null;
        var username = "username";
        var request = new CreateUserWithOAuthRequest(
                provider,
                email,
                username,
                null
        );

        // when
        var throwable = Assertions.catchThrowable(() -> userCreateWithOAuthService.createUser(request));

        // then
        Assertions.assertThat(throwable).isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("정상적인 인자라면 유저가 생성된다")
    void CREATE_USER_WHEN_VALID_INPUT() {
        // given
        var provider = Provider.KAKAO;
        var email = "test@test.com";
        var username = "username";
        var request = new CreateUserWithOAuthRequest(
                provider,
                email,
                username,
                null
        );

        // when
        var throwable = Assertions.catchThrowable(() -> userCreateWithOAuthService.createUser(request));

        // then
        Assertions.assertThat(throwable).isNull();
    }
}
