package me.nalab.user.jpa.adaptor.create.repository;

import me.nalab.core.data.user.UserOAuthInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserOAuthInfoJpaRepository extends JpaRepository<UserOAuthInfoEntity, Long> {
    Optional<UserOAuthInfoEntity> findByProviderAndEmail(String provider, String email);
}
