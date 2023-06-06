package me.nalab.user.application.service;

import lombok.RequiredArgsConstructor;
import me.nalab.core.idgenerator.idcore.IdGenerator;
import me.nalab.user.application.common.dto.CreateUserWithOAuthRequest;
import me.nalab.user.application.port.in.UserCreateWithOAuthUseCase;
import me.nalab.user.application.port.out.persistence.UserCreateWithOAuthPort;
import me.nalab.user.domain.user.User;
import me.nalab.user.domain.user.UserOAuthInfo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserCreateWithOAuthService implements UserCreateWithOAuthUseCase {

    private final UserCreateWithOAuthPort userCreateWithOAuthPort;
    private final IdGenerator idGenerator;

    @Override
    public long createUser(CreateUserWithOAuthRequest request) {
        var provider = request.getProvider();
        var email = request.getEmail();
        Objects.requireNonNull(provider, "유저를 생성하기 위해서는 제공자 값은 필수입니다.");
        Objects.requireNonNull(email, "유저를 생성하기 위해서는 이메일 값은 필수입니다.");

        var userId = idGenerator.generate();
        var now = LocalDateTime.now();

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

        return userCreateWithOAuthPort.createUserWithOAuth(user, userOAuthInfo);
    }
}
