package me.nalab.luffy.api.acceptance.test.user.updatetarget;

import static me.nalab.luffy.api.acceptance.test.user.UserAcceptanceValidator.*;

import java.time.Instant;

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

import me.nalab.auth.mock.api.MockUserRegisterEvent;
import me.nalab.luffy.api.acceptance.test.TargetInitializer;
import me.nalab.luffy.api.acceptance.test.user.UserAcceptanceTestSupporter;
import me.nalab.luffy.api.acceptance.test.user.updatetarget.request.RequestSample;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("classpath:h2.properties")
@ComponentScan("me.nalab")
@EnableJpaRepositories(basePackages = {"me.nalab"})
@EntityScan(basePackages = {"me.nalab"})
class TargetPositionUpdateAcceptanceTest extends UserAcceptanceTestSupporter {

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@Autowired
	private TargetInitializer targetInitializer;

	@Test
	@DisplayName("로그인된 타겟의 position 수정 성공 테스트")
	void TARGET_POSITION_UPDATE_SUCCESS_TEST() throws Exception {
		// given
		String nickname = "nickname";
		Long targetId = targetInitializer.saveTargetAndGetId(nickname, Instant.now());
		String token = "token";
		applicationEventPublisher.publishEvent(MockUserRegisterEvent.builder()
			.expectedToken(token)
			.expectedId(targetId)
			.build());

		// when
		ResultActions resultActions = updateTargetPosition(token, RequestSample.DEFAULT_JSON);

		// then
		assertIsTargetPositionUpdated(resultActions);
	}

}
