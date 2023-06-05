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

@Service
@RequiredArgsConstructor
public class UserCreateWithOAuthService implements UserCreateWithOAuthUseCase {

    private final UserCreateWithOAuthPort userCreateWithOAuthPort;
    private final IdGenerator idGenerator;

    @Override
    public void createUser(CreateUserWithOAuthRequest request) {
        var userId = idGenerator.generate();
        var now = LocalDateTime.now();

        var user = new User(userId, request.getUsername(), request.getEmail(), now, now);
        var userOAuthInfo = new UserOAuthInfo(
                idGenerator.generate(),
                request.getProvider(),
                request.getEmail(),
                request.getUsername(),
                request.getPhoneNumber(),
                now,
                now,
                userId
        );

        userCreateWithOAuthPort.createUserWithOAuth(user, userOAuthInfo);
    }
}
