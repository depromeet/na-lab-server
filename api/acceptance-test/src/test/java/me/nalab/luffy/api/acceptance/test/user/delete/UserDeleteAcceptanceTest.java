package me.nalab.luffy.api.acceptance.test.user.delete;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;
import java.util.Set;
import me.nalab.auth.application.common.dto.Payload;
import me.nalab.auth.application.common.utils.JwtUtils;
import me.nalab.auth.mock.api.MockUserRegisterEvent;
import me.nalab.luffy.api.acceptance.test.TargetInitializer;
import me.nalab.luffy.api.acceptance.test.UserInitializer;
import me.nalab.luffy.api.acceptance.test.user.UserAcceptanceTestSupporter;
import me.nalab.user.domain.user.Provider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("classpath:h2.properties")
@ComponentScan("me.nalab")
@EnableJpaRepositories(basePackages = {"me.nalab"})
@EntityScan(basePackages = {"me.nalab"})
class UserDeleteAcceptanceTest extends UserAcceptanceTestSupporter {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private TargetInitializer targetInitializer;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserInitializer userInitializer;

    @Test
    @DisplayName("DELETE /v1/users API는 token에 해당하는 유저가 삭제되면 200 OK를 반환한다.")
    void DELETE_USER_SUCCESS() throws Exception {
        // given
        String nickname = "delete_user_success";
        String email = "delete_user_success";

        var token = "bearer " + createUserAndSetToken(nickname, email);

        // when
        var result = deleteUser(token);

        // then
        result.andExpect(status().isOk());
    }

    private String createUserAndSetToken(String nickname, String email) {
        Long userId = userInitializer.saveUserWithOAuth(Provider.DEFAULT, nickname, email, Instant.now());
        Long targetId = targetInitializer.saveTargetAndGetId(nickname, Instant.now());

        var token = jwtUtils.createAccessToken(Set.of(new Payload(Payload.Key.USER_ID, String.valueOf(userId)),
            new Payload(Payload.Key.TARGET_ID, String.valueOf(targetId))));

        applicationEventPublisher.publishEvent(
            MockUserRegisterEvent.builder().expectedToken("bearer " + token).expectedId(targetId).build());

        return token;
    }
}
