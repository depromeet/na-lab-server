package me.nalab.user.jpa.adaptor.create;

import java.time.LocalDateTime;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import me.nalab.user.application.common.dto.FindByProviderAndTokenRequest;
import me.nalab.user.domain.user.Provider;
import me.nalab.user.jpa.adaptor.create.repository.UserJpaRepository;

@DataJpaTest
@EnableJpaRepositories
@EntityScan("me.nalab.core.data")
@ContextConfiguration(classes = UserFindByProviderAndTokenAdaptor.class)
@TestPropertySource("classpath:h2.properties")
class UserFindByProviderAndTokenAdaptorTest {


	@Autowired
	private UserFindByProviderAndTokenAdaptor userFindByProviderAndTokenAdaptor;

	@Autowired
	private UserJpaRepository userJpaRepository;

	@ParameterizedTest
	@EnumSource(Provider.class)
	@DisplayName("해당 provider와 token에 해당하는 user가 없다면, empty를 반환한다")
	void RETURN_EMPTY_WHEN_NOT_FIND_USER(Provider provider) {
		// given
		var token = "token";
		var request = new FindByProviderAndTokenRequest.Out(provider, token);

		// when
		var result = userFindByProviderAndTokenAdaptor.findByProviderAndToken(request);

		// then
		Assertions.assertThat(result).isEmpty();
	}

	@ParameterizedTest
	@EnumSource(Provider.class)
	@DisplayName("해당 provider와 token에 해당하는 user가 있다면, user를 반환한다")
	void RETURN_USER_ID_WHEN_FIND_USER(Provider provider) {
		// given
		var token = "token";
		var request = new FindByProviderAndTokenRequest.Out(provider, token);
		var userEntity = UserTestUtils.createUserEntity(1L, provider, token, LocalDateTime.now(), LocalDateTime.now());
		userJpaRepository.saveAndFlush(userEntity);

		// when
		var result = userFindByProviderAndTokenAdaptor.findByProviderAndToken(request);

		// then
		Assertions.assertThat(result).isPresent();
		var user = result.get();
		Assertions.assertThat(user.getProvider()).isEqualTo(provider);
		Assertions.assertThat(user.getToken()).isEqualTo(token);
	}}

