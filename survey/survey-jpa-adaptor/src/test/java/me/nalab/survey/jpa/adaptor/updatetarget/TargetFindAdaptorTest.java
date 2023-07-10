package me.nalab.survey.jpa.adaptor.updatetarget;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;
import java.util.Optional;

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
import me.nalab.survey.domain.target.Target;

@DataJpaTest
@EnableJpaRepositories
@EntityScan("me.nalab.core.data")
@ContextConfiguration(classes = TargetFindAdaptor.class)
@TestPropertySource("classpath:h2.properties")
class TargetFindAdaptorTest {

	@Autowired
	private TargetFindAdaptor targetFindAdaptor;

	@Autowired
	private TestTargetFindRepository testTargetFindRepository;

	@Autowired
	private EntityManager entityManager;

	@Test
	@DisplayName("타겟 조회 성공 테스트")
	void FIND_TARGET_BY_ID_SUCCESS() {

		Long targetId = 1L;
		TargetEntity targetEntity = TargetEntity.builder()
			.id(targetId)
			.createdAt(Instant.now())
			.updatedAt(Instant.now())
			.nickname("sujin")
			.build();

		testTargetFindRepository.saveAndFlush(targetEntity);
		entityManager.clear();
		Optional<Target> resultTarget = targetFindAdaptor.findTarget(targetId);

		assertTrue(resultTarget.isPresent());
		assertEquals(targetEntity.getId(), resultTarget.get().getId());
	}

	@Test
	@DisplayName("타겟 조회 실패 테스트")
	void FIND_TARGET_BY_ID_FAIL() {

		Long targetId = 1L;

		Optional<Target> target = targetFindAdaptor.findTarget(targetId);

		assertTrue(target.isEmpty());
	}

}
