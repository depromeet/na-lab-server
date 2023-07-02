package me.nalab.user.jpa.adaptor.create.common.mapper;

import me.nalab.core.data.user.UserEntity;
import me.nalab.user.jpa.adaptor.create.UserTestUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;

class UserObjectMapperTest {

	private static final String VALID_NICKNAME = "nickname";
	private static final String VALID_EMAIL = "test@test.com";



	@Test
	@DisplayName("유저 도메인 객체 엔티티로 변환 성공 테스트")
	void USER_DOMAIN_TO_ENTITY_SUCCESS() {
		// given
		var id = 1L;
		var createdAt = Instant.now();
		var userDomain = UserTestUtils.createUserDomain(id, VALID_NICKNAME, VALID_EMAIL, createdAt, null);

		// when
		var entity = UserObjectMapper.toEntity(userDomain);

		// then
		UserTestUtils.assertEquals(entity, userDomain);
	}

	@Test
	@DisplayName("유저 도메인 객체 엔티티로 변환 성공 테스트 - provider, token null")
	void USER_DOMAIN_TO_ENTITY_SUCCESS_WITH_NULL_PROVIDER() {
		// given
		var id = 1L;
		var createdAt = Instant.now();
		var userDomain = UserTestUtils.createUserDomain(id, null, null, createdAt, null);

		// when
		var entity = UserObjectMapper.toEntity(userDomain);

		// then
		UserTestUtils.assertEquals(entity, userDomain);
	}

	@Test
	@DisplayName("유저 도메인 객체 엔티티로 변환 실패 테스트 - 도메인 객체 null")
	void NULL_USER_DOMAIN_TO_ENTITY_FAIL() {
		// given
		// when
		var throwable = Assertions.catchThrowable(() -> UserObjectMapper.toEntity(null));

		// then
		Assertions.assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
	}
	@Test
	@DisplayName("유저 엔티티 객체 도메인으로 변환 성공 테스트")
	void USER_ENTITY_TO_DOMAIN_SUCCESS() {
		// given
		var entity = createUserEntity();

		// when
		var domain = UserObjectMapper.toDomain(entity);

		// then
		UserTestUtils.assertEquals(domain, entity);
	}

	@Test
	@DisplayName("유저 엔티티 객체 도메인으로 변환 성공 테스트 - provider, token null")
	void USER_ENTITY_TO_DOMAIN_SUCCESS_WITH_PROVIDER_NULL() {
		// given
		var id = 1L;
		var createdAt = Instant.now();
		var entity = UserTestUtils.createUserEntity(id, null, null, createdAt, null);

		// when
		var domain = UserObjectMapper.toDomain(entity);

		// then
		UserTestUtils.assertEquals(domain, entity);
	}

	@Test
	@DisplayName("유저 엔티티 객체 도메인으로 변환 실패 테스트 - 엔티티 객체 null")
	void NULL_USER_ENTITY_TO_DOMAIN_FAIL() {
		// given
		// when
		var throwable = Assertions.catchThrowable(() -> UserObjectMapper.toDomain(null));

		// then
		Assertions.assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
	}

	private UserEntity createUserEntity() {
		var id = 1L;
		var createdAt = Instant.now();

		return UserTestUtils.createUserEntity(id, VALID_NICKNAME, VALID_EMAIL, createdAt, null);
	}
}
