package me.nalab.user.application.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import me.nalab.user.application.common.dto.FindByProviderAndTokenRequest;
import me.nalab.user.application.port.in.FindByProviderAndTokenUseCase;
import me.nalab.user.application.port.out.persistence.UserFindByProviderAndTokenPort;
import me.nalab.user.domain.user.User;

@Service
@RequiredArgsConstructor
public class FindByProviderAndTokenService implements FindByProviderAndTokenUseCase {

	private final UserFindByProviderAndTokenPort userFindByProviderAndTokenPort;

	@Override
	public Optional<Long> findByProviderAndToken(FindByProviderAndTokenRequest request) {
		return userFindByProviderAndTokenPort.findByProviderAndToken(request).map(User::getId);
	}
}
