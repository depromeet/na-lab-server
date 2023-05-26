package me.nalab.user.jpa.adaptor.create.common.mapper;

import java.time.LocalDateTime;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import me.nalab.user.domain.user.Provider;
import me.nalab.user.jpa.adaptor.create.UserTestUtils;

class UserEntityMapperTest {

	@Test
	@DisplayName("유저 도메인 객체 엔티티로 변환 성공 테스트")
	void USER_DOMAIN_TO_ENTITY_SUCCESS() {
		// given
		var id = 1L;
		var provider = Provider.KAKAO;
		var token = UUID.randomUUID().toString();
		var createdAt = LocalDateTime.now();
		var userDomain = UserTestUtils.createUserDomain(id, provider, token, createdAt, null);

		// when
		var entity = UserEntityMapper.toEntity(userDomain);

		// then
		UserTestUtils.assertEquals(entity, userDomain);
	}

	@Test
	@DisplayName("유저 도메인 객체 엔티티로 변환 성공 테스트 - provider, token null")
	void USER_DOMAIN_TO_ENTITY_SUCCESS_WITH_NULL_PROVIDER() {
		// given
		var id = 1L;
		Provider provider = null;
		String token = null;
		var createdAt = LocalDateTime.now();
		var userDomain = UserTestUtils.createUserDomain(id, provider, token, createdAt, null);

		// when
		var entity = UserEntityMapper.toEntity(userDomain);

		// then
		UserTestUtils.assertEquals(entity, userDomain);
	}

	@Test
	@DisplayName("유저 도메인 객체 엔티티로 변환 실패 테스트 - 도메인 객체 null")
	void NULL_USER_DOMAIN_TO_ENTITY_FAIL() {
		// given
		// when
		var throwable = Assertions.catchThrowable(() -> UserEntityMapper.toEntity(null));

		// then
		Assertions.assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
	}
	@Test
	@DisplayName("유저 엔티티 객체 도메인으로 변환 성공 테스트")
	void USER_ENTITY_TO_DOMAIN_SUCCESS() {
		// given
		var id = 1L;
		var provider = Provider.KAKAO;
		var token = UUID.randomUUID().toString();
		var createdAt = LocalDateTime.now();
		var entity = UserTestUtils.createUserEntity(id, provider, token, createdAt, null);

		// when
		var domain = UserEntityMapper.toDomain(entity);

		// then
		UserTestUtils.assertEquals(domain, entity);
	}

	@Test
	@DisplayName("유저 엔티티 객체 도메인으로 변환 성공 테스트 - provider, token null")
	void USER_ENTITY_TO_DOMAIN_SUCCESS_WITH_PROVIDER_NULL() {
		// given
		var id = 1L;
		Provider provider = null;
		String token = null;
		var createdAt = LocalDateTime.now();
		var entity = UserTestUtils.createUserEntity(id, provider, token, createdAt, null);

		// when
		var domain = UserEntityMapper.toDomain(entity);

		// then
		UserTestUtils.assertEquals(domain, entity);
	}

	@Test
	@DisplayName("유저 엔티티 객체 도메인으로 변환 실패 테스트 - 엔티티 객체 null")
	void NULL_USER_ENTITY_TO_DOMAIN_FAIL() {
		// given
		// when
		var throwable = Assertions.catchThrowable(() -> UserEntityMapper.toDomain(null));

		// then
		Assertions.assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
	}
}
