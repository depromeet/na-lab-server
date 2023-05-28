package me.nalab.user.application.common.dto;

import lombok.Data;
import me.nalab.user.domain.user.Provider;


public class FindByProviderAndTokenRequest {
	@Data
	public static class In {
		private final String providerName;
		private final String token;
	}

	@Data
	public static class Out {
		private final Provider provider;
		private final String token;
	}
}
