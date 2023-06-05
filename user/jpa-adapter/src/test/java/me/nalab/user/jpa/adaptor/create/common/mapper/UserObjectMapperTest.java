package me.nalab.user.jpa.adaptor.create.common.mapper;

import me.nalab.core.data.user.UserEntity;
import me.nalab.user.domain.user.Provider;
import me.nalab.user.jpa.adaptor.create.UserTestUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

class UserObjectMapperTest {

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
		var entity = UserObjectMapper.toEntity(userDomain);

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
		Provider provider = null;
		String token = null;
		var createdAt = LocalDateTime.now();
		var entity = UserTestUtils.createUserEntity(id, provider, token, createdAt, null);

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

	@Test
	@DisplayName("유저 엔티티 객체 도메인으로 변환 실패 테스트 - oauth 정보들 객체 null")
	void USER_TO_DOMAIN_WITH_NULL_OAUTH_INFO_ENTITY_LIST_FAIL() {
		// given
		var entity = createUserEntity();

		// when
		var throwable = Assertions.catchThrowable(() -> UserObjectMapper.toDomain(entity));

		// then
		Assertions.assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
	}

	private UserEntity createUserEntity() {
		var id = 1L;
		var provider = Provider.KAKAO;
		var token = UUID.randomUUID().toString();
		var createdAt = LocalDateTime.now();

		return UserTestUtils.createUserEntity(id, provider, token, createdAt, null);
	}
}
