package me.nalab.user.application.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import me.nalab.user.application.common.dto.FindByProviderAndTokenRequest;
import me.nalab.user.application.port.out.persistence.UserFindByProviderAndTokenPort;
import me.nalab.user.domain.user.Provider;
import me.nalab.user.domain.user.User;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {UserFindByProviderAndTokenService.class})
class UserFindByProviderAndTokenServiceTest {

	@MockBean
	private UserFindByProviderAndTokenPort userFindByProviderAndTokenPort;

	@Autowired
	private UserFindByProviderAndTokenService userFindByProviderAndTokenService;

	@Test
	@DisplayName("Provider 이름에 해당하는 Provider가 없다면, 예외를 발생시킨다")
	void THROW_EXCEPTION_WHEN_DO_NOT_MATCHED_PROVIDER_NAME() {
		// given
		var providerName = "not provider name";
		var token = "token";
		var request = new FindByProviderAndTokenRequest.In(providerName, token);

		// when
		var throwable = Assertions.catchThrowable(() ->
			userFindByProviderAndTokenService.findByProviderAndToken(request)
		);

		// then
		Assertions.assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
	}

	@ParameterizedTest
	@EnumSource(Provider.class)
	@DisplayName("해당 provider와 token에 해당하는 user가 없다면, empty를 반환한다")
	void RETURN_EMPTY_WHEN_NOT_FIND_USER(Provider provider) {
		// given
		var providerName = provider.name();
		var token = "token";
		var request = new FindByProviderAndTokenRequest.In(providerName, token);

		// when
		var result = userFindByProviderAndTokenService.findByProviderAndToken(request);

		// then
		Assertions.assertThat(result).isEmpty();
	}

	@ParameterizedTest
	@EnumSource(Provider.class)
	@DisplayName("해당 provider와 token에 해당하는 user가 있다면, user Id를 반환한다")
	void RETURN_USER_ID_WHEN_FIND_USER(Provider provider) {
		// given
		var providerName = provider.name();
		var token = "token";
		var request = new FindByProviderAndTokenRequest.In(providerName, token);
		var expectedUserId = 1L;
		var user = new User(expectedUserId, provider, token, LocalDateTime.now(), null);

		when(userFindByProviderAndTokenPort.findByProviderAndToken(any())).thenReturn(Optional.of(user));

		// when
		var result = userFindByProviderAndTokenService.findByProviderAndToken(request);

		// then
		Assertions.assertThat(result)
			.isPresent()
			.containsSame(expectedUserId);
	}
}
