package me.nalab.auth.application.common.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Getter
@Component
@PropertySource("classpath:application.properties")
public class JwtProperties {

	private final String accessTokenHeader;
	private final String issuer;
	private final String prefix;
	private final String secret;
	private final Long accessTokenExpirySeconds;

	public JwtProperties(@Value("${jwt.access-token-header}") String accessTokenHeader,
		@Value("${jwt.issuer}") String issuer,
		@Value("${jwt.prefix}") String prefix,
		@Value("${jwt.secret}") String secret,
		@Value("${jwt.accessTokenExpirySeconds}") Long accessTokenExpirySeconds) {
		this.accessTokenHeader = accessTokenHeader;
		this.issuer = issuer;
		this.prefix = prefix;
		this.secret = secret;
		this.accessTokenExpirySeconds = accessTokenExpirySeconds;
	}

}
