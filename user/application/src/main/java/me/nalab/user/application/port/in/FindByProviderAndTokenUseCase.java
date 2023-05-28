package me.nalab.user.application.port.in;

import java.util.Optional;

import me.nalab.user.application.common.dto.FindByProviderAndTokenRequest;

/**
 * provider와 token을 이용해 user를 조회합니다.
 */
public interface FindByProviderAndTokenUseCase {

	/**
	 * Provider와 Token 값에 해당하는 유저를 찾고 식별자를 반환합니다.
	 * @param request ProviderName, Token을 포함하는 요청 객체
	 * @return user 식별자 혹은 Optional.Empty()
	 */
	Optional<Long> findByProviderAndToken(FindByProviderAndTokenRequest request);
}

