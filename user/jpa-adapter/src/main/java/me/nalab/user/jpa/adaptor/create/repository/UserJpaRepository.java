package me.nalab.user.jpa.adaptor.create.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import me.nalab.core.data.user.UserEntity;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long>{
}
