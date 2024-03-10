package me.nalab.user.application.service;

import java.util.Objects;

import me.nalab.user.application.common.dto.LoginedInfo;
import me.nalab.user.application.port.out.persistence.UserGetPort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import me.nalab.user.application.exception.InvalidTokenException;
import me.nalab.user.application.port.in.LoginedUserGetByTokenUseCase;
import me.nalab.user.application.port.out.persistence.LoginedUserGetByTokenPort;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoginedUserGetByTokenService implements LoginedUserGetByTokenUseCase {

	private final LoginedUserGetByTokenPort loginedUserGetByTokenPort;
	private final UserGetPort userGetPort;

	@Override
	@Transactional(readOnly = true)
	public LoginedInfo getLoginedInfoByToken(String encryptedToken) {
		Objects.requireNonNull(encryptedToken, "encryptedToken은 null이 되면 안됩니다.");
		var tokenInfo = loginedUserGetByTokenPort.decryptToken(encryptedToken);
		var user = userGetPort.getById(tokenInfo.userId());
		return LoginedInfo.from(tokenInfo.targetId(), user);
	}

}
