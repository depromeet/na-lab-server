package me.nalab.auth.application.common.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@EqualsAndHashCode(of = "key")
@RequiredArgsConstructor
public class Payload {
	private final Key key;
	private final String value;

	public enum Key {
		USER_ID,
		NICKNAME,
	}
}
