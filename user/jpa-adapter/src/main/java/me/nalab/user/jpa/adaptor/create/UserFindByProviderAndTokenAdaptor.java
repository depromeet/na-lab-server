package me.nalab.user.jpa.adaptor.create;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import lombok.RequiredArgsConstructor;
import me.nalab.user.application.common.dto.FindByProviderAndTokenRequest;
import me.nalab.user.application.port.out.persistence.UserFindByProviderAndTokenPort;
import me.nalab.user.domain.user.Provider;
import me.nalab.user.jpa.adaptor.create.repository.UserJpaRepository;

@Component
@RequiredArgsConstructor
public class UserFindByProviderAndTokenAdaptor implements UserFindByProviderAndTokenPort {

	private final UserJpaRepository userJpaRepository;

	@Override
	public long findByProviderAndToken(FindByProviderAndTokenRequest request) {
		Assert.notNull(request, "");
		Assert.notNull(request.getToken(), "");

		var provider = Provider.valueOf(request.getProviderName());
		var token = request.getToken();

		var userEntity = userJpaRepository.findByProviderAndToken(provider, token);

		return userEntity.getId();
	}
}
