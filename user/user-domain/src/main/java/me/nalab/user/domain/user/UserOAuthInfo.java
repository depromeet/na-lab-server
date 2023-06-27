package me.nalab.user.domain.user;

import java.time.Instant;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode(of = {"id"})
@AllArgsConstructor
public class UserOAuthInfo {
	private final long id;
	private final Provider provider;
	private final String email;
	private final String username;
	private final String phoneNumber;
	private final Instant createdAt;
	private Instant updatedAt;
	private final Long userId;

	/**
	 * 동일한 유저가 회원 가입 후 로그인 시도시에 같은 유저인지 식별하는 값을 조회
	 * 식별자는 provider에 의존적
	 * @return identifier
	 */
	public String getIdentifier() {
		Objects.requireNonNull(provider, "OAuthInfo(id = "+id+")의 provider가 존재하지 않습니다.");
		Objects.requireNonNull(email, "OAuthInfo(id = "+id+")의 기본 식별자(email)이 존재하지 않습니다.");

		// 유일한 KAKAO Provider의 식별값(email)과 기본 식별값(email)이 동일하여 한번에 반환한다.
		// Provider가 추가될 경우 분기 처리가 필요
		return email;
	}
}
