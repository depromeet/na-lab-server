package me.nalab.user.application.common.dto;

import lombok.Data;

@Data
public class TokenInfo {

	private final String nickName;
	private final Long targetId;
	private final Long userId;

}
