package me.nalab.auth.application.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.auth0.jwt.interfaces.DecodedJWT;

import lombok.RequiredArgsConstructor;
import me.nalab.auth.application.common.dto.Payload;
import me.nalab.auth.application.common.utils.JwtUtils;
import me.nalab.user.application.common.dto.LoginedInfo;
import me.nalab.user.application.port.out.persistence.LoginedUserGetByTokenPort;

@Service
@RequiredArgsConstructor
public class JwtLoginedDecryptService implements LoginedUserGetByTokenPort {

	private final JwtUtils jwtUtils;

	@Override
	public LoginedInfo decryptToken(String encryptedToken) {
		Assert.isTrue(encryptedToken != null && !encryptedToken.isBlank(),
			"encryptedToken 으로 blank나 null 값이 들어올 수 없습니다.");
		DecodedJWT decodedJWT = jwtUtils.verify(encryptedToken);
		String nickName = decodedJWT.getClaim(Payload.Key.NICKNAME.name()).asString();
		Long userId = decodedJWT.getClaim(Payload.Key.USER_ID.name()).asLong();
		Long targetId = decodedJWT.getClaim(Payload.Key.TARGET_ID.name()).asLong();
		return new LoginedInfo(nickName, userId, targetId);
	}

}
