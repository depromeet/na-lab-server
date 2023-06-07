package me.nalab.luffy.api.acceptance.test.user.logined;

import java.time.LocalDateTime;
import java.util.Set;

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
import org.springframework.test.web.servlet.ResultActions;

import me.nalab.auth.application.common.dto.Payload;
import me.nalab.auth.application.common.utils.JwtUtils;
import me.nalab.auth.mock.api.MockUserRegisterEvent;
import me.nalab.luffy.api.acceptance.test.TargetInitializer;
import me.nalab.luffy.api.acceptance.test.user.UserAcceptanceTestSupporter;
import static me.nalab.luffy.api.acceptance.test.user.UserAcceptanceValidator.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("classpath:h2.properties")
@ComponentScan("me.nalab")
@EnableJpaRepositories(basePackages = {"me.nalab"})
@EntityScan(basePackages = {"me.nalab"})
class LoginedTargetFindAcceptanceTest extends UserAcceptanceTestSupporter {

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@Autowired
	private TargetInitializer targetInitializer;

	@Autowired
	private JwtUtils jwtUtils;

	@Test
	@DisplayName("로그인된 유저 정보 조회 성공 테스트")
	void GET_LOGINED_USER_SUCCESS() throws Exception{
		// given
		String nickname = "devxb";
		Long targetId = targetInitializer.saveTargetAndGetId(nickname, LocalDateTime.now());
		String token = jwtUtils.createAccessToken(Set.of(new Payload(Payload.Key.NICKNAME, nickname),
			new Payload(Payload.Key.USER_ID, 12345 + ""), new Payload(Payload.Key.TARGET_ID, targetId + "")));
		applicationEventPublisher.publishEvent(
			MockUserRegisterEvent.builder().expectedToken(token).expectedId(targetId).build());

		// when
		ResultActions resultActions = getLoginedUser(token);

		// then
		assertIsLogined(resultActions, targetId, nickname);
	}

}
