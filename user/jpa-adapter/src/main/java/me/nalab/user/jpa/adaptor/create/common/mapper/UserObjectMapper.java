package me.nalab.user.jpa.adaptor.create.common.mapper;

import org.springframework.util.Assert;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import me.nalab.core.data.user.UserEntity;
import me.nalab.user.domain.user.Provider;
import me.nalab.user.domain.user.User;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserObjectMapper {
	public static UserEntity toEntity(User user) {
		Assert.notNull(user, "");
		Provider provider = user.getProvider();
		return UserEntity.builder()
			.id(user.getId())
			.provider(provider == null ? null : provider.name())
			.token(user.getToken())
			.createdAt(user.getCreatedAt())
			.updatedAt(user.getUpdatedAt())
			.build();
	}

	public static User toDomain(UserEntity entity) {
		Assert.notNull(entity, "");
		String providerName = entity.getProvider();
		return User.builder()
			.id(entity.getId())
			.provider(providerName == null ? null : Provider.valueOf(providerName))
			.token(entity.getToken())
			.createdAt(entity.getCreatedAt())
			.updatedAt(entity.getUpdatedAt())
			.build();
	}
}
