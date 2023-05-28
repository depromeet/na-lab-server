package me.nalab.user.application.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import me.nalab.user.application.common.dto.FindByProviderAndTokenRequest;
import me.nalab.user.application.port.in.UserFindByProviderAndTokenUseCase;
import me.nalab.user.application.port.out.persistence.UserFindByProviderAndTokenPort;
import me.nalab.user.domain.user.Provider;
import me.nalab.user.domain.user.User;

@Service
@RequiredArgsConstructor
public class UserFindByProviderAndTokenService implements UserFindByProviderAndTokenUseCase {

	private final UserFindByProviderAndTokenPort userFindByProviderAndTokenPort;

	@Override
	public Optional<Long> findByProviderAndToken(FindByProviderAndTokenRequest.In request) {
		Provider provider = Provider.valueOf(request.getProviderName());
		String token = request.getToken();
		var outRequest = new FindByProviderAndTokenRequest.Out(provider, token);
		return userFindByProviderAndTokenPort.findByProviderAndToken(outRequest).map(User::getId);
	}
}
