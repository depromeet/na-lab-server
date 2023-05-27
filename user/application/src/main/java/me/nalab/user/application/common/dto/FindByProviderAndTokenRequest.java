package me.nalab.user.application.common.dto;

import lombok.Data;


@Data
public class FindByProviderAndTokenRequest {
	private final String providerName;
	private final String token;
}
