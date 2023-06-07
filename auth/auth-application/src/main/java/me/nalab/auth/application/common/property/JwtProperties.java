package me.nalab.auth.application.common.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Getter;
import me.nalab.auth.application.config.YamlPropertySourceFactory;

@Getter
@Component
@PropertySource(value = "classpath:application.yml", factory = YamlPropertySourceFactory.class)
public class JwtProperties {

	private final String accessTokenHeader;
	private final String issuer;
	private final String prefix;
	private final String secret;
	private final Long accessTokenExpirySeconds;

	public JwtProperties(
		@Value("${jwt.access-token-header:Authorization}") String accessTokenHeader,
		@Value("${jwt.issuer:issuer}") String issuer,
		@Value("${jwt.prefix:prefix}") String prefix,
		@Value("${jwt.secret:secret}") String secret,
		@Value("${jwt.accessTokenExpirySeconds:3600}") Long accessTokenExpirySeconds
) {
		this.accessTokenHeader = accessTokenHeader;
		this.issuer = issuer;
		this.prefix = prefix;
		this.secret = secret;
		this.accessTokenExpirySeconds = accessTokenExpirySeconds;
	}

}
