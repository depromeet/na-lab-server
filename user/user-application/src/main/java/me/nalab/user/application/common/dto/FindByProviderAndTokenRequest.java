package me.nalab.user.application.common.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.nalab.user.domain.user.Provider;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FindByProviderAndTokenRequest {
	@Data
	public static class In {
		@NotBlank
		private final String providerName;
		@NotBlank
		private final String token;
	}

	@Data
	public static class Out {
		@NotNull
		private final Provider provider;
		@NotBlank
		private final String token;
	}
}
