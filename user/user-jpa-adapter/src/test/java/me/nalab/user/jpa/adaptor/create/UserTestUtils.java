package me.nalab.user.jpa.adaptor.create;

import me.nalab.core.data.user.UserEntity;
import me.nalab.user.domain.user.User;
import org.assertj.core.api.Assertions;

import java.time.Instant;

public class UserTestUtils {

	public static User createUserDomain(long id, String nickname, String email, Instant createdAt, Instant updatedAt) {
		return User.builder()
			.id(id)
			.nickname(nickname)
			.email(email)
			.createdAt(createdAt)
			.updatedAt(updatedAt)
			.build();
	}

	public static UserEntity createUserEntity(long id, String nickname, String email, Instant createdAt, Instant updatedAt) {
		return UserEntity.builder()
			.id(id)
			.nickname(nickname)
			.email(email)
			.createdAt(createdAt)
			.updatedAt(updatedAt)
			.build();
	}

	public static void assertEquals(UserEntity entity, User domain) {
		Assertions.assertThat(entity.getId()).isEqualTo(domain.getId());
		Assertions.assertThat(entity.getNickname()).isEqualTo(domain.getNickname());
		Assertions.assertThat(entity.getEmail()).isEqualTo(domain.getEmail());
		Assertions.assertThat(entity.getCreatedAt()).isEqualTo(domain.getCreatedAt());
		Assertions.assertThat(entity.getUpdatedAt()).isEqualTo(domain.getUpdatedAt());
	}

	public static void assertEquals(User domain, UserEntity entity) {
		Assertions.assertThat(domain.getId()).isEqualTo(entity.getId());
		Assertions.assertThat(domain.getNickname()).isEqualTo(entity.getNickname());
		Assertions.assertThat(domain.getEmail()).isEqualTo(entity.getEmail());
		Assertions.assertThat(domain.getCreatedAt()).isEqualTo(entity.getCreatedAt());
		Assertions.assertThat(domain.getUpdatedAt()).isEqualTo(entity.getUpdatedAt());
	}
}
