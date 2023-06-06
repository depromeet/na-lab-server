package me.nalab.user.jpa.adaptor.create;

import lombok.RequiredArgsConstructor;
import me.nalab.user.application.port.out.persistence.UserCreateWithOAuthPort;
import me.nalab.user.domain.user.User;
import me.nalab.user.domain.user.UserOAuthInfo;
import me.nalab.user.jpa.adaptor.create.common.mapper.UserOAuthInfoMapper;
import me.nalab.user.jpa.adaptor.create.repository.UserJpaRepository;
import me.nalab.user.jpa.adaptor.create.repository.UserOAuthInfoJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserCreateWithOAuthAdaptor implements UserCreateWithOAuthPort {

    private final UserJpaRepository userJpaRepository;
    private final UserOAuthInfoJpaRepository userOAuthInfoJpaRepository;

    @Override
    public void createUserWithOAuth(User user, UserOAuthInfo userOAuthInfo) {
        var userOAuthInfoEntity = UserOAuthInfoMapper.toEntity(userOAuthInfo, user);
        var userEntity = userOAuthInfoEntity.getUserEntity();

        userJpaRepository.save(userEntity);
        userOAuthInfoJpaRepository.save(userOAuthInfoEntity);
    }
}
