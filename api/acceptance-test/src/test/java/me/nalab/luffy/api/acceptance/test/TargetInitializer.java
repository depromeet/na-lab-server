package me.nalab.luffy.api.acceptance.test;

import java.time.Instant;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import me.nalab.core.data.target.TargetEntity;
import me.nalab.core.idgenerator.idcore.IdGenerator;

@Component
public class TargetInitializer {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private IdGenerator idGenerator;

	@Transactional
	public Long saveTargetAndGetId(String name, Instant date) {
		TargetEntity targetEntity = TargetEntity.builder()
			.id(idGenerator.generate())
			.nickname(name)
			.createdAt(date)
			.updatedAt(date)
			.imageUrl("empty image")
			.build();
		entityManager.persist(targetEntity);
		return targetEntity.getId();
	}

}
