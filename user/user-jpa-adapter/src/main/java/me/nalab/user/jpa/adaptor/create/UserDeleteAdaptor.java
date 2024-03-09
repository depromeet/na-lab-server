package me.nalab.user.jpa.adaptor.create;

import lombok.RequiredArgsConstructor;
import me.nalab.user.application.port.out.persistence.UserDeletePort;
import me.nalab.user.jpa.adaptor.create.repository.UserJpaRepository;
import me.nalab.user.jpa.adaptor.create.repository.UserOAuthInfoJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserDeleteAdaptor implements UserDeletePort {

    private final UserJpaRepository userJpaRepository;
    private final UserOAuthInfoJpaRepository userOAuthInfoJpaRepository;

    @Override
    public void deleteUserById(Long userId) {
        var user = userJpaRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("Cannot find exist user"));
        userOAuthInfoJpaRepository.deleteByUserId(user.getId());
        userJpaRepository.deleteById(user.getId());
    }
}
