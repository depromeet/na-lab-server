package me.nalab.user.jpa.adaptor.create;

import java.time.LocalDateTime;

import org.assertj.core.api.Assertions;

import me.nalab.core.data.user.UserEntity;
import me.nalab.user.domain.user.Provider;
import me.nalab.user.domain.user.User;

public class UserTestUtils {

	public static User createUserDomain(long id, Provider provider, String token, LocalDateTime createdAt, LocalDateTime updatedAt) {
		return User.builder()
			.id(id)
			.provider(provider)
			.token(token)
			.createdAt(createdAt)
			.updatedAt(updatedAt)
			.build();
	}

	public static UserEntity createUserEntity(long id, Provider provider, String token, LocalDateTime createdAt, LocalDateTime updatedAt) {
		return UserEntity.builder()
			.id(id)
			.provider(provider == null ? null : provider.name())
			.token(token)
			.createdAt(createdAt)
			.updatedAt(updatedAt)
			.build();
	}

	public static void assertEquals(UserEntity entity, User domain) {
		Assertions.assertThat(entity.getId()).isEqualTo(domain.getId());
		Assertions.assertThat(entity.getProvider()).isEqualTo(domain.getProvider() == null ? null : domain.getProvider().name());
		Assertions.assertThat(entity.getToken()).isEqualTo(domain.getToken());
		Assertions.assertThat(entity.getCreatedAt()).isEqualTo(domain.getCreatedAt());
		Assertions.assertThat(entity.getUpdatedAt()).isEqualTo(domain.getUpdatedAt());
	}

	public static void assertEquals(User domain, UserEntity entity) {
		Assertions.assertThat(domain.getId()).isEqualTo(entity.getId());
		Assertions.assertThat(domain.getProvider() == null ? null : domain.getProvider().name()).isEqualTo(entity.getProvider());
		Assertions.assertThat(domain.getToken()).isEqualTo(entity.getToken());
		Assertions.assertThat(domain.getCreatedAt()).isEqualTo(entity.getCreatedAt());
		Assertions.assertThat(domain.getUpdatedAt()).isEqualTo(entity.getUpdatedAt());
	}
}
