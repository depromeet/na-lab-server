package me.nalab.auth.application.common.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "jwt")
public final class JwtProperties {
	private final String accessTokenHeader;
	private final String issuer;
	private final String prefix;
	private final String secret;
	private final Long accessTokenExpirySeconds;
}
