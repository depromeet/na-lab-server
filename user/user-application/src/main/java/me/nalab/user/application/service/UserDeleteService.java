package me.nalab.user.application.service;

import lombok.RequiredArgsConstructor;
import me.nalab.user.application.port.in.UserDeleteUseCase;
import me.nalab.user.application.port.out.persistence.LoginedUserGetByTokenPort;
import me.nalab.user.application.port.out.persistence.UserDeletePort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserDeleteService implements UserDeleteUseCase {

    private final LoginedUserGetByTokenPort loginedUserGetByTokenPort;
    private final UserDeletePort userDeletePort;

    @Override
    @Transactional
    public void deleteByToken(String token) {
        var tokenInfo = loginedUserGetByTokenPort.decryptToken(token);
        userDeletePort.deleteUserById(tokenInfo.userId());
    }
}
