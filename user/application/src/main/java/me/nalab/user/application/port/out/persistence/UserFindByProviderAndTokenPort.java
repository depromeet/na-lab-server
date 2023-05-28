package me.nalab.user.application.port.out.persistence;

import java.util.Optional;

import me.nalab.user.application.common.dto.FindByProviderAndTokenRequest;
import me.nalab.user.domain.user.User;

/**
 *  Provider와 Token을 이용해서 유저를 조회합니다.
 */
public interface UserFindByProviderAndTokenPort {

	/**
	 * user domain 조회
	 * @param request ProviderName, Token을 포함하는 요청 객체
	 * @return user 도메인 객체 혹은 Optional.Empty()
	 */
	Optional<User> findByProviderAndToken(FindByProviderAndTokenRequest request);
}
