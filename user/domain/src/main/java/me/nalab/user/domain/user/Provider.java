package me.nalab.user.domain.user;

public enum Provider {

	KAKAO("kakao"),
	;

	final String displayName;

	Provider(String displayName) {
		this.displayName = displayName;
	}
}
