package me.nalab.auth.application.port.in.web;

/**
 * 복호화된 타겟의 id를 반환합니다.
 */
public interface TargetIdGetPort {

	/**
	 * token을 인자로 받아, 복호화된 타겟의 id를 반환합니다.
	 *
	 * @param token 암호화된 토큰
	 * @return 복호화된 타겟의 id
	 */
	Long getTargetId(String token);

}
