package me.nalab.auth.application.common.dto;

import lombok.Data;

@Data
public class CreateAuthTokenRequest {
	private final String userId;
	private final String nickname;
	private final String targetId;
}
