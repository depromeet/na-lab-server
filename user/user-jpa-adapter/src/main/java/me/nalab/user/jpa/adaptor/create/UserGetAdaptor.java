package me.nalab.user.jpa.adaptor.create;

import lombok.RequiredArgsConstructor;
import me.nalab.user.application.port.out.persistence.UserGetPort;
import me.nalab.user.domain.user.User;
import me.nalab.user.jpa.adaptor.create.common.mapper.UserObjectMapper;
import me.nalab.user.jpa.adaptor.create.repository.UserJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserGetAdaptor implements UserGetPort {

    private final UserJpaRepository userJpaRepository;

    @Override
    public User getById(Long id) {
        var user = userJpaRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException(String.format("Cannot find exists user \"%d\"", id)));

        return UserObjectMapper.toDomain(user);
    }
}
