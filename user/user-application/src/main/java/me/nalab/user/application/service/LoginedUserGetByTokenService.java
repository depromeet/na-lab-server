package me.nalab.user.application.service;

import java.util.Objects;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import me.nalab.user.application.common.dto.LoginedInfo;
import me.nalab.user.application.port.in.LoginedUserGetByTokenUseCase;
import me.nalab.user.application.port.out.persistence.LoginedUserGetByTokenPort;

@Service
@RequiredArgsConstructor
public class LoginedUserGetByTokenService implements LoginedUserGetByTokenUseCase {

	private final LoginedUserGetByTokenPort loginedUserGetByTokenPort;

	@Override
	public LoginedInfo decryptToken(String encryptedToken) {
		Objects.requireNonNull(encryptedToken, "encryptedToken은 null이 되면 안됩니다.");
		return loginedUserGetByTokenPort.decryptToken(encryptedToken);
	}

}
