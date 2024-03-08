package me.nalab.auth.application.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.auth0.jwt.interfaces.DecodedJWT;

import lombok.RequiredArgsConstructor;
import me.nalab.auth.application.common.dto.Payload;
import me.nalab.auth.application.common.utils.JwtUtils;
import me.nalab.auth.application.port.in.web.TargetIdGetPort;
import me.nalab.user.application.common.dto.TokenInfo;
import me.nalab.user.application.port.out.persistence.LoginedUserGetByTokenPort;

@Service
@RequiredArgsConstructor
public class JwtLoginedDecryptService implements LoginedUserGetByTokenPort, TargetIdGetPort {

	private final JwtUtils jwtUtils;

	@Override
	public TokenInfo decryptToken(String encryptedToken) {
		Assert.isTrue(encryptedToken != null && !encryptedToken.isBlank(),
			"encryptedToken 으로 blank나 null 값이 들어올 수 없습니다.");
		DecodedJWT decodedJWT = jwtUtils.verify(encryptedToken);
		String nickName = decodedJWT.getClaim(Payload.Key.NICKNAME.name()).asString();
		Long userId = Long.valueOf(decodedJWT.getClaim(Payload.Key.USER_ID.name()).asString());
		Long targetId = Long.valueOf(decodedJWT.getClaim(Payload.Key.TARGET_ID.name()).asString());
		return new TokenInfo(nickName, targetId, userId);
	}

	@Override
	public Long getTargetId(String encryptedToken) {
		DecodedJWT decodedJWT = jwtUtils.verify(encryptedToken);
		return Long.valueOf(decodedJWT.getClaim(Payload.Key.TARGET_ID.name()).asString());
	}
}
