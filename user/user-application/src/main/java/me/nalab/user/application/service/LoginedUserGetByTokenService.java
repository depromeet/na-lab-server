package me.nalab.user.application.service;

import java.util.Objects;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import me.nalab.user.application.common.dto.LoginedInfo;
import me.nalab.user.application.exception.InvalidTokenException;
import me.nalab.user.application.port.in.LoginedUserGetByTokenUseCase;
import me.nalab.user.application.port.out.persistence.LoginedUserGetByTokenPort;

@Service
@RequiredArgsConstructor
public class LoginedUserGetByTokenService implements LoginedUserGetByTokenUseCase {

	private final LoginedUserGetByTokenPort loginedUserGetByTokenPort;

	@Override
	public LoginedInfo decryptToken(String encryptedToken) {
		Objects.requireNonNull(encryptedToken, "encryptedToken은 null이 되면 안됩니다.");
		String[] split = encryptedToken.split(" ");
		throwIfInvalidToken(split);
		return loginedUserGetByTokenPort.decryptToken(split[1]);
	}

	private void throwIfInvalidToken(String[] split) {
		if(split.length < 2) {
			throw new InvalidTokenException(split[0]);
		}
	}

}
