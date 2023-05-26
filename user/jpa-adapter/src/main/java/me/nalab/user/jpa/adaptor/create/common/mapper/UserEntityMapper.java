package me.nalab.user.jpa.adaptor.create.common.mapper;

import me.nalab.core.data.user.UserEntity;
import me.nalab.user.domain.user.Provider;
import me.nalab.user.domain.user.User;

public class UserEntityMapper {
	public static UserEntity toEntity(User user) {
		return UserEntity.builder()
			.id(user.getId())
			.provider(user.getProvider().name())
			.token(user.getToken())
			.createdAt(user.getCreatedAt())
			.updatedAt(user.getUpdatedAt())
			.build();
	}

	public static User toDomain(UserEntity entity) {
		return User.builder()
			.id(entity.getId())
			.provider(Provider.valueOf(entity.getProvider()))
			.token(entity.getToken())
			.createdAt(entity.getCreatedAt())
			.updatedAt(entity.getUpdatedAt())
			.build();
	}
}
