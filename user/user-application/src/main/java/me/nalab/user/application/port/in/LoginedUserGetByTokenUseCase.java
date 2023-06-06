package me.nalab.user.application.port.in;

import me.nalab.user.application.common.dto.LoginedInfo;

/**
 * token을 받아 로그인된 유저의 정보를 반환하는 유즈케이스 입니다.
 */
public interface LoginedUserGetByTokenUseCase {

	/**
	 * 암호화된 유저의 토큰을 받아, 복호화된 유저의 정보를 반환합니다.
	 *
	 * @param encryptedToken 암호화된 토큰
	 * @return 복호화된 정보
	 */
	LoginedInfo decryptToken(String encryptedToken);

}
