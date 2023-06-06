package me.nalab.user.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 *  OAuth 제공자
 */
@Getter
@RequiredArgsConstructor
public enum Provider {
	KAKAO(true),
	;

	final boolean isActive;
}
