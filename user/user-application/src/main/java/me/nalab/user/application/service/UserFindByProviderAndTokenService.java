package me.nalab.user.application.service;

import lombok.RequiredArgsConstructor;
import me.nalab.user.application.common.dto.FindByProviderAndTokenRequest;
import me.nalab.user.application.port.in.UserFindByProviderAndTokenUseCase;
import me.nalab.user.application.port.out.persistence.UserFindByProviderAndTokenPort;
import me.nalab.user.domain.user.Provider;
import me.nalab.user.domain.user.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserFindByProviderAndTokenService implements UserFindByProviderAndTokenUseCase {

	private final UserFindByProviderAndTokenPort userFindByProviderAndTokenPort;

	@Override
	public Optional<Long> findByProviderAndToken(FindByProviderAndTokenRequest.In request) {
		var providerName = request.getProviderName();
		var provider = Provider.valueOf(providerName);
		var token = request.getToken();
		var outRequest = new FindByProviderAndTokenRequest.Out(provider, token);

		return userFindByProviderAndTokenPort.findByProviderAndToken(outRequest).map(User::getId);
	}
}
