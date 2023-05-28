package me.nalab.user.jpa.adaptor.create.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import me.nalab.core.data.user.UserEntity;
import me.nalab.user.domain.user.Provider;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long>{
	Optional<UserEntity> findByProviderAndToken(Provider provider, String token);
}
