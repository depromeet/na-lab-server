package me.nalab.user.jpa.adaptor.create.common.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import me.nalab.core.data.user.UserEntity;
import me.nalab.user.domain.user.User;
import org.springframework.util.Assert;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserObjectMapper {
	public static UserEntity toEntity(User user) {
		Assert.notNull(user, "");
		return UserEntity.builder()
			.id(user.getId())
			.nickname(user.getNickname())
			.email(user.getEmail())
			.createdAt(user.getCreatedAt())
			.updatedAt(user.getUpdatedAt())
			.build();
	}

	public static User toDomain(UserEntity entity) {
		Assert.notNull(entity, "");
		return User.builder()
			.id(entity.getId())
			.email(entity.getEmail())
			.nickname(entity.getNickname())
			.createdAt(entity.getCreatedAt())
			.updatedAt(entity.getUpdatedAt())
			.build();
	}
}
