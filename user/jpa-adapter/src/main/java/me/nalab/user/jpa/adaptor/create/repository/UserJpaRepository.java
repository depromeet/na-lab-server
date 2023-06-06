package me.nalab.user.jpa.adaptor.create.repository;

import me.nalab.core.data.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
}
