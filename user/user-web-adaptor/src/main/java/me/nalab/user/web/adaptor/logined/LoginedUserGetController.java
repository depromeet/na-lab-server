package me.nalab.user.web.adaptor.logined;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import me.nalab.user.application.port.in.LoginedUserGetByTokenUseCase;
import me.nalab.user.web.adaptor.logined.response.LoginedInfoResponse;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class LoginedUserGetController {

	private final LoginedUserGetByTokenUseCase loginedUserGetByTokenUseCase;

	@GetMapping("/users/logins")
	public LoginedInfoResponse getLoginedUserByToken(@RequestAttribute("tokenValue") String token) {
		return LoginedInfoResponse.of(loginedUserGetByTokenUseCase.getLoginedInfoByToken(token));
	}

}
