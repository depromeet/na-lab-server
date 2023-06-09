package me.nalab.user.jpa.adaptor.create.common.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import me.nalab.core.data.user.UserOAuthInfoEntity;
import me.nalab.user.domain.user.Provider;
import me.nalab.user.domain.user.User;
import me.nalab.user.domain.user.UserOAuthInfo;
import org.springframework.util.Assert;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserOAuthInfoMapper {

	public static UserOAuthInfoEntity toEntity(UserOAuthInfo domain, User user) {
		Assert.notNull(domain, "");
		var userEntity = UserObjectMapper.toEntity(user);
		return UserOAuthInfoEntity.builder()
			.id(domain.getId())
			.provider(domain.getProvider().name())
			.username(domain.getUsername())
			.email(domain.getEmail())
			.phoneNumber(domain.getPhoneNumber())
			.createdAt(domain.getCreatedAt())
			.updatedAt(domain.getUpdatedAt())
			.userEntity(userEntity)
			.build();
	}

	public static UserOAuthInfo toDomain(UserOAuthInfoEntity entity) {
		Assert.notNull(entity, "");
		Provider provider = Provider.valueOf(entity.getProvider());
		return UserOAuthInfo.builder()
			.id(entity.getId())
			.provider(provider)
			.email(entity.getEmail())
			.username(entity.getUsername())
			.phoneNumber(entity.getPhoneNumber())
			.createdAt(entity.getCreatedAt())
			.updatedAt(entity.getUpdatedAt())
			.userId(entity.getUserEntity().getId())
			.build();
	}

}
