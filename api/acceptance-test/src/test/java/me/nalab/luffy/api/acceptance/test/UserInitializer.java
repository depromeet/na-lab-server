package me.nalab.luffy.api.acceptance.test;

import me.nalab.core.data.user.UserEntity;
import me.nalab.core.data.user.UserOAuthInfoEntity;
import me.nalab.core.idgenerator.idcore.IdGenerator;
import me.nalab.user.domain.user.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.Instant;

@Component
public class UserInitializer {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private IdGenerator idGenerator;

	@Transactional
	public void saveUserWithOAuth(Provider provider, String name, String email, Instant date) {
		var userEntity = UserEntity.builder()
			.id(idGenerator.generate())
			.nickname(name)
			.email(email)
			.createdAt(date)
			.updatedAt(date)
			.build();
		var userOauthInfoEntity = UserOAuthInfoEntity.builder()
				.id(idGenerator.generate())
				.provider(provider.name())
				.username(name)
				.email(email)
				.createdAt(date)
				.updatedAt(date)
				.userEntity(userEntity)
				.build();

		entityManager.persist(userEntity);
		entityManager.persist(userOauthInfoEntity);
	}

}
