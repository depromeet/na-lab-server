package me.nalab.user.jpa.adaptor.create;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import me.nalab.user.application.port.out.persistence.UserCreatePort;
import me.nalab.user.domain.user.User;
import me.nalab.user.jpa.adaptor.create.common.mapper.UserObjectMapper;
import me.nalab.user.jpa.adaptor.create.repository.UserJpaRepository;

@Repository
@RequiredArgsConstructor
public class UserCreateAdaptor implements UserCreatePort {

	private final UserJpaRepository userRepository;

	@Override
	public void createUser(User user) {
		var userEntity = UserObjectMapper.toEntity(user);
		userRepository.save(userEntity);
	}
}
