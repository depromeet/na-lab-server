package me.nalab.user.domain.user;

import lombok.RequiredArgsConstructor;

/**
 *  OAuth 제공자
 */
@RequiredArgsConstructor
public enum Provider {
	KAKAO(true),
	;

	private final boolean isActive;
}
