package me.nalab.survey.jpa.adaptor.create;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import me.nalab.core.data.target.TargetEntity;

@DataJpaTest
@EnableJpaRepositories
@EntityScan("me.nalab.core.data")
@ContextConfiguration(classes = TargetExistCheckAdaptor.class)
@TestPropertySource("classpath:h2.properties")
class TargetExistCheckAdaptorTest {

	@Autowired
	private TargetExistCheckAdaptor targetExistCheckAdaptor;

	@Autowired
	private TestTargetJpaRepository testTargetJpaRepository;

	@Autowired
	private EntityManager entityManager;

	@Test
	@DisplayName("Target존재하는지 확인 테스트")
	void CHECK_EXIST_TARGET_SUCCESS() {
		// given
		TargetEntity targetEntity = TargetEntity.builder()
			.id(1L)
			.nickname("Hello")
			.createdAt(LocalDateTime.now())
			.updatedAt(LocalDateTime.now())
			.build();

		testTargetJpaRepository.saveAndFlush(targetEntity);
		entityManager.clear();

		// when
		boolean existTrue = targetExistCheckAdaptor.isExistTargetByTargetId(1L);
		boolean existFalse = targetExistCheckAdaptor.isExistTargetByTargetId(2L);

		// then
		assertTrue(existTrue);
		assertFalse(existFalse);
	}

}
