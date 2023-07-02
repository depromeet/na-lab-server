package me.nalab.user.jpa.adaptor.create;

import java.time.Instant;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import me.nalab.user.jpa.adaptor.create.repository.UserJpaRepository;

@DataJpaTest
@EnableJpaRepositories
@EntityScan("me.nalab.core.data")
@ContextConfiguration(classes = UserCreateAdaptor.class)
@TestPropertySource("classpath:h2.properties")
class UserCreateAdaptorTest {

	@Autowired
	private UserCreateAdaptor userCreateAdaptor;

	@Autowired
	private UserJpaRepository userJpaRepository;

	@Test
	@DisplayName("유저 생성 성공 테스트")
	void CREATE_USER_SUCCESS() {
		// given
		var id = 1L;
		var nickname = "nickname";
		var email = "test@test.com";
		var user = UserTestUtils.createUserDomain(id, nickname, email, Instant.now(), null);

		// when
		userCreateAdaptor.createUser(user);

		// given;
		var isExist = userJpaRepository.existsById(id);
		Assertions.assertThat(isExist).isTrue();
	}
}
