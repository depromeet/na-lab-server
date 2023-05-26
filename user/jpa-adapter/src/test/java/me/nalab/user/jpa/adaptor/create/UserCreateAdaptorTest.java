package me.nalab.user.jpa.adaptor.create;

import java.time.LocalDateTime;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import me.nalab.user.domain.user.Provider;
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
		var provider = Provider.KAKAO;
		var token = UUID.randomUUID().toString();
		var user = UserTestUtils.createUserDomain(id, provider, token, LocalDateTime.now(), null);

		// when
		userCreateAdaptor.createUser(user);

		// given
		var isExist = userJpaRepository.existsById(id);
		Assertions.assertThat(isExist).isTrue();
	}

	@Test
	@DisplayName("유저 생성 성공 테스트 - provider와 token null")
	void CREATE_USER_SUCCESS2() {
		// given
		var id = 1L;
		Provider provider = null;
		String token = null;
		var user = UserTestUtils.createUserDomain(id, provider, token, LocalDateTime.now(), null);

		// when
		userCreateAdaptor.createUser(user);

		// given
		var isExist = userJpaRepository.existsById(id);
		Assertions.assertThat(isExist).isTrue();
	}



}
