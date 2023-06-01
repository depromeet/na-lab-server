package me.nalab.auth.application.service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import lombok.RequiredArgsConstructor;
import me.nalab.auth.application.common.dto.Payload;
import me.nalab.auth.application.common.property.JwtProperties;

@Service
@RequiredArgsConstructor
public class JwtService {

	private final JwtProperties properties;

	public String createAccessToken(Set<Payload> payload) {
		var payloadMap = new HashMap<String, String>();
		payload.forEach(p -> payloadMap.put(p.getKey().name(), p.getValue()));

		return createJwt(payloadMap);
	}

	public DecodedJWT verify(String jwt) {
		var algorithm = getAlgorithm();
		var verifier = getVerifier(algorithm);

		return verifier.verify(jwt);
	}

	private JWTVerifier getVerifier(Algorithm algorithm) {
		return JWT.require(algorithm)
			.withIssuer(properties.getIssuer())
			.build();
	}

	private String createJwt(Map<String, String> payload) {
		var algorithm = getAlgorithm();

		var issuer = properties.getIssuer();
		var accessTokenExpirySeconds = properties.getAccessTokenExpirySeconds();
		var expiresAt = Instant.now().plusSeconds(accessTokenExpirySeconds);

		return JWT.create()
			.withIssuer(issuer)
			.withPayload(payload)
			.withExpiresAt(expiresAt)
			.sign(algorithm);
	}

	private Algorithm getAlgorithm() {
		return Algorithm.HMAC256(properties.getSecret());
	}
}
