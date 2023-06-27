package me.nalab.user.jpa.adaptor.create.common.mapper;

import java.time.Instant;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import me.nalab.core.data.user.UserEntity;
import me.nalab.user.domain.user.User;
import me.nalab.user.jpa.adaptor.create.UserOAuthInfoTestUtils;
import me.nalab.user.jpa.adaptor.create.UserTestUtils;

class UserOAuthInfoMapperTest {

	private static final long VALID_ID = 1L;
	private static final String VALID_NICKNAME = "nickname";
	private static final String VALID_EMAIL = "test@test.com";

	@Test
	@DisplayName("유저 도메인 객체 엔티티로 변환 성공 테스트")
	void USER_OAUTH_INFO_DOMAIN_TO_ENTITY_SUCCESS() {
		// given
		var userDomain = getUserDomain();
		var domain = UserOAuthInfoTestUtils.createDomain()
			.userId(userDomain.getId())
			.build();

		// when
		var entity = UserOAuthInfoMapper.toEntity(domain, userDomain);

		// then
		UserOAuthInfoTestUtils.assertEquals(domain, entity);
	}

	@Test
	@DisplayName("유저 도메인 객체 엔티티로 변환 실패 테스트 - 도메인 객체 null")
	void NULL_USER_DOMAIN_TO_ENTITY_FAIL() {
		// given
		var userDomain = getUserDomain();

		// when
		var throwable = Assertions.catchThrowable(() -> UserOAuthInfoMapper.toEntity(null, userDomain));

		// then
		Assertions.assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("유저 엔티티 객체 도메인으로 변환 성공 테스트")
	void USER_ENTITY_TO_DOMAIN_SUCCESS() {
		// given
		var userEntity = createUserEntity();
		var entity = UserOAuthInfoTestUtils.createEntity()
			.userEntity(userEntity)
			.build();

		// when
		var domain = UserOAuthInfoMapper.toDomain(entity);

		// then
		UserOAuthInfoTestUtils.assertEquals(entity, domain);
	}

	@Test
	@DisplayName("유저 엔티티 객체 도메인으로 변환 성공 테스트 - provider, token null")
	void USER_ENTITY_TO_DOMAIN_SUCCESS_WITH_PROVIDER_NULL() {
		// given
		var userEntity = createUserEntity();
		var entity = UserOAuthInfoTestUtils.createEntity()
			.userEntity(userEntity)
			.build();

		// when
		var domain = UserOAuthInfoMapper.toDomain(entity);

		// then
		UserOAuthInfoTestUtils.assertEquals(domain, entity);
	}

	@Test
	@DisplayName("유저 엔티티 객체 도메인으로 변환 실패 테스트 - 엔티티 객체 null")
	void NULL_USER_ENTITY_TO_DOMAIN_FAIL() {
		// given
		// when
		var throwable = Assertions.catchThrowable(() -> UserOAuthInfoMapper.toDomain(null));

		// then
		Assertions.assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
	}

	private UserEntity createUserEntity() {
		var id = 1L;
		var createdAt = Instant.now();

		return UserTestUtils.createUserEntity(id, VALID_NICKNAME, VALID_EMAIL, createdAt, null);
	}

	private User getUserDomain() {
		var now = Instant.now();
		return UserTestUtils.createUserDomain(VALID_ID, VALID_NICKNAME, VALID_EMAIL, now, now);
	}
}
