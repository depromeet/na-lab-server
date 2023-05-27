package me.nalab.user.application.port.out.persistence;

import me.nalab.user.application.common.dto.FindByProviderAndTokenRequest;

/**
 *  Provider와 Token을 이용해서 유저를 조회합니다.
 */
public interface UserFindByProviderAndTokenPort {

	/**
	 * user domain 조회
	 * @param request ProviderName, Token을 포함하는 요청 객체
	 */
	long findByProviderAndToken(FindByProviderAndTokenRequest request);
}
