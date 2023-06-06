package me.nalab.user.jpa.adaptor.create;

import me.nalab.user.jpa.adaptor.create.repository.UserJpaRepository;
import me.nalab.user.jpa.adaptor.create.repository.UserOAuthInfoJpaRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;

@DataJpaTest
@EnableJpaRepositories
@EntityScan("me.nalab.core.data")
@ContextConfiguration(classes = UserCreateWithOAuthAdaptor.class)
@TestPropertySource("classpath:h2.properties")
class UserCreateWithOAuthAdaptorTest {

    @Autowired
    private UserCreateWithOAuthAdaptor userCreateWithOAuthAdaptor;

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Autowired
    private UserOAuthInfoJpaRepository userOAuthInfoJpaRepository;

    @Test
    @DisplayName("유저와 유저 인증 정보 생성 성공 테스트")
    void CREATE_USER_AND_USER_OAUTH_INFO_SUCCESS() {
        // given
        var id = 1L;
        var nickname = "nickname";
        var email = "test@test.com";
        var user = UserTestUtils.createUserDomain(id, nickname, email, LocalDateTime.now(), null);
        var userOAuthInfo = UserOAuthInfoTestUtils.createDomain().userId(user.getId()).build();

        // when
        userCreateWithOAuthAdaptor.createUserWithOAuth(user, userOAuthInfo);

        // given;
        var userIsExist = userJpaRepository.existsById(id);
        var userOAuthInfoIsExist = userOAuthInfoJpaRepository.existsById(userOAuthInfo.getId());
        Assertions.assertThat(userIsExist).isTrue();
        Assertions.assertThat(userOAuthInfoIsExist).isTrue();
    }

}
