package me.nalab.user.jpa.adaptor.create;

import me.nalab.core.data.user.UserOAuthInfoEntity;
import me.nalab.user.domain.user.Provider;
import me.nalab.user.domain.user.UserOAuthInfo;
import org.assertj.core.api.Assertions;

import java.time.LocalDateTime;

public class UserOAuthInfoTestUtils {

	public static final String DEFAULT_EMAIL = "test@test.com";
	public static final Provider DEFAULT_PROVIDER = Provider.KAKAO;
	public static final long DEFAULT_ID = 1L;
	public static final String DEFAULT_USERNAME = "username";
	public static final String DEFAULT_PHONE_NUMBER = null;

	public static UserOAuthInfo.UserOAuthInfoBuilder createDomain() {
		var now = LocalDateTime.now();
		return UserOAuthInfo.builder()
			.id(DEFAULT_ID)
			.provider(DEFAULT_PROVIDER)
			.email(DEFAULT_EMAIL)
			.username(DEFAULT_USERNAME)
			.phoneNumber(DEFAULT_PHONE_NUMBER)
			.createdAt(now)
			.updatedAt(now);
	}

	public static UserOAuthInfoEntity.UserOAuthInfoEntityBuilder createEntity() {
		var now = LocalDateTime.now();
		return UserOAuthInfoEntity.builder()
			.id(DEFAULT_ID)
			.provider(DEFAULT_PROVIDER.name())
			.email(DEFAULT_EMAIL)
			.username(DEFAULT_USERNAME)
			.phoneNumber(DEFAULT_PHONE_NUMBER)
			.createdAt(now)
			.updatedAt(now);
	}

	public static void assertEquals(UserOAuthInfoEntity entity, UserOAuthInfo domain) {
		Assertions.assertThat(entity.getId()).isEqualTo(domain.getId());
		Assertions.assertThat(entity.getProvider()).isEqualTo(domain.getProvider().name());
		Assertions.assertThat(entity.getEmail()).isEqualTo(domain.getEmail());
		Assertions.assertThat(entity.getUsername()).isEqualTo(domain.getUsername());
		Assertions.assertThat(entity.getCreatedAt()).isEqualTo(domain.getCreatedAt());
		Assertions.assertThat(entity.getUpdatedAt()).isEqualTo(domain.getUpdatedAt());
		Assertions.assertThat(entity.getPhoneNumber()).isEqualTo(domain.getPhoneNumber());
	}

	public static void assertEquals(UserOAuthInfo domain, UserOAuthInfoEntity entity) {
		Assertions.assertThat(domain.getId()).isEqualTo(entity.getId());
		Assertions.assertThat(domain.getProvider().name()).isEqualTo(entity.getProvider());
		Assertions.assertThat(domain.getEmail()).isEqualTo(entity.getEmail());
		Assertions.assertThat(domain.getUsername()).isEqualTo(entity.getUsername());
		Assertions.assertThat(domain.getCreatedAt()).isEqualTo(entity.getCreatedAt());
		Assertions.assertThat(domain.getUpdatedAt()).isEqualTo(entity.getUpdatedAt());
		Assertions.assertThat(domain.getPhoneNumber()).isEqualTo(entity.getPhoneNumber());
	}
}
