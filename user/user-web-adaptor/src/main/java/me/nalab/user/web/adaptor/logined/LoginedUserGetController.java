package me.nalab.user.web.adaptor.logined;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import me.nalab.user.application.common.dto.LoginedInfo;
import me.nalab.user.application.port.in.LoginedUserGetByTokenUseCase;
import me.nalab.user.web.adaptor.logined.response.LoginedInfoResponse;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class LoginedUserGetController {

	private final LoginedUserGetByTokenUseCase loginedUserGetByTokenUseCase;

	@GetMapping("/users/logined")
	public LoginedInfoResponse getLoginedUserByToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
		LoginedInfo loginedInfo = loginedUserGetByTokenUseCase.decryptToken(token);

		return new LoginedInfoResponse(String.valueOf(loginedInfo.getTargetId()), loginedInfo.getNickName());
	}

}
