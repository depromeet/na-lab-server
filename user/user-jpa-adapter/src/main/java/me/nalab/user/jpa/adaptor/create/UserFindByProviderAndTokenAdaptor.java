package me.nalab.user.jpa.adaptor.create;

import lombok.RequiredArgsConstructor;
import me.nalab.user.application.common.dto.FindByProviderAndTokenRequest;
import me.nalab.user.application.port.out.persistence.UserFindByProviderAndTokenPort;
import me.nalab.user.domain.user.User;
import me.nalab.user.jpa.adaptor.create.common.mapper.UserObjectMapper;
import me.nalab.user.jpa.adaptor.create.repository.UserOAuthInfoJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserFindByProviderAndTokenAdaptor implements UserFindByProviderAndTokenPort {

	private final UserOAuthInfoJpaRepository userOAuthInfoJpaRepository;

	@Override
	public Optional<User> findByProviderAndToken(FindByProviderAndTokenRequest.Out request) {
		var provider = request.getProvider();
		var token = request.getToken();

		var userOAuthInfoEntity = userOAuthInfoJpaRepository.findByProviderAndEmail(provider.name(), token);
		if (userOAuthInfoEntity.isEmpty()) {
			return Optional.empty();
		}

		var userEntity = userOAuthInfoEntity.get().getUserEntity();

		if (userEntity == null) {
			return Optional.empty();
		}

		var user = UserObjectMapper.toDomain(userEntity);
		return Optional.of(user);
	}
}
