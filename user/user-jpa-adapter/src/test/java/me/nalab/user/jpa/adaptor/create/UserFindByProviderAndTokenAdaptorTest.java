package me.nalab.user.jpa.adaptor.create;

import java.time.Instant;

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

import me.nalab.core.data.user.UserOAuthInfoEntity;
import me.nalab.user.application.common.dto.FindByProviderAndTokenRequest;
import me.nalab.user.domain.user.Provider;
import me.nalab.user.jpa.adaptor.create.repository.UserJpaRepository;
import me.nalab.user.jpa.adaptor.create.repository.UserOAuthInfoJpaRepository;

@DataJpaTest
@EnableJpaRepositories
@EntityScan("me.nalab.core.data")
@ContextConfiguration(classes = UserFindByProviderAndTokenAdaptor.class)
@TestPropertySource("classpath:h2.properties")
class UserFindByProviderAndTokenAdaptorTest {

	@Autowired
	private UserFindByProviderAndTokenAdaptor userFindByProviderAndTokenAdaptor;

	@Autowired
	private UserOAuthInfoJpaRepository userOAuthInfoJpaRepository;

	@Autowired
	private UserJpaRepository userJpaRepository;

	@ParameterizedTest
	@EnumSource(Provider.class)
	@DisplayName("해당 provider와 token에 해당하는 userOAuth가 없다면, empty를 반환한다")
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
		var nickname = "nickname";
		var userEntity = UserTestUtils.createUserEntity(1L, nickname, "email", Instant.now(), Instant.now());
		userJpaRepository.save(userEntity);
		var oauthInfoEntity = new UserOAuthInfoEntity(1L, provider.name(), token, nickname, null, userEntity);
		userOAuthInfoJpaRepository.saveAndFlush(oauthInfoEntity);

		// when
		var result = userFindByProviderAndTokenAdaptor.findByProviderAndToken(request);

		// then
		Assertions.assertThat(result).isPresent();
	}
}

