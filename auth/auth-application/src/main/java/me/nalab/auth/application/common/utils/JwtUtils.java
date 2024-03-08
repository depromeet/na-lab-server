package me.nalab.auth.application.common.utils;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.IncorrectClaimException;
import com.auth0.jwt.exceptions.MissingClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import me.nalab.auth.application.common.exception.AuthException;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import lombok.RequiredArgsConstructor;
import me.nalab.auth.application.common.dto.Payload;
import me.nalab.auth.application.common.property.JwtProperties;

@Component
@RequiredArgsConstructor
public class JwtUtils {

	private final JwtProperties properties;

	public String createAccessToken(Set<Payload> payload) {
		var payloadMap = new HashMap<String, String>();
		payload.forEach(p -> payloadMap.put(p.getKey().name(), p.getValue()));

		return createJwt(payloadMap);
	}

	public DecodedJWT verify(String jwt) {
		var algorithm = getAlgorithm();
		var verifier = getVerifier(algorithm);

		try {
			return verifier.verify(jwt);
		} catch (TokenExpiredException tokenExpiredException) {
			throw new AuthException("Expired token");
		} catch (IncorrectClaimException
				 | MissingClaimException
				 | SignatureVerificationException
				 | AlgorithmMismatchException invalidTokenException) {
			throw new AuthException("Invalid token");
		}
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
