package me.nalab.user.jpa.adaptor.create.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import me.nalab.core.data.user.UserEntity;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long>{
	Optional<UserEntity> findByProviderAndToken(String provider, String token);
}
