package me.nalab.user.application.service;

import java.util.Objects;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import me.nalab.core.idgenerator.idcore.IdGenerator;
import me.nalab.core.time.TimeUtil;
import me.nalab.survey.application.common.target.dto.CreateTargetRequest;
import me.nalab.survey.application.port.in.web.CreateTargetUseCase;
import me.nalab.user.application.common.dto.CreateUserWithOAuthRequest;
import me.nalab.user.application.port.in.UserCreateWithOAuthUseCase;
import me.nalab.user.application.port.out.persistence.UserCreateWithOAuthPort;
import me.nalab.user.domain.user.User;
import me.nalab.user.domain.user.UserOAuthInfo;

@Service
@RequiredArgsConstructor
public class UserCreateWithOAuthService implements UserCreateWithOAuthUseCase {

	private final UserCreateWithOAuthPort userCreateWithOAuthPort;
	private final CreateTargetUseCase createTargetUseCase;
	private final IdGenerator idGenerator;
	private final TimeUtil timeUtil;

	@Override
	public long createUser(CreateUserWithOAuthRequest request) {
		var provider = request.getProvider();
		var email = request.getEmail();
		Objects.requireNonNull(provider, "유저를 생성하기 위해서는 제공자 값은 필수입니다.");
		Objects.requireNonNull(email, "유저를 생성하기 위해서는 이메일 값은 필수입니다.");

		var userId = idGenerator.generate();
		var now = timeUtil.toInstant();

		var user = new User(userId, request.getUsername(), email, now, now);
		var userOAuthInfo = new UserOAuthInfo(
			idGenerator.generate(),
			provider,
			email,
			request.getUsername(),
			request.getPhoneNumber(),
			now,
			now,
			userId
		);

		// 테스트 코드 작성 필요
		var createTargetRequest = new CreateTargetRequest(request.getUsername());
		createTargetUseCase.create(createTargetRequest);

		return userCreateWithOAuthPort.createUserWithOAuth(user, userOAuthInfo);
	}
}
