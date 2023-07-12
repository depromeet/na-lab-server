package me.nalab.survey.jpa.adaptor.findtarget;

import static org.junit.jupiter.api.Assertions.*;

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
import me.nalab.survey.jpa.adaptor.common.mapper.TargetEntityMapper;

@DataJpaTest
@EnableJpaRepositories
@EntityScan("me.nalab.core.data")
@ContextConfiguration(classes = TargetFindAdaptor.class)
@TestPropertySource("classpath:h2.properties")
class TargetFindAdaptorTest {

	@Autowired
	private TargetFindAdaptor targetFindAdaptor;

	@Autowired
	private TestTargetFindJpaRepository targetFindJpaRepository;

	@Autowired
	private EntityManager entityManager;

	@Test
	@DisplayName("targetId로 target 조회 성공테스트")
	void FIND_TARGET_BY_TARGET_ID_SUCCESS_TEST() {

		Long targetId = 1L;
		TargetEntity targetEntity = TargetEntity.builder()
			.id(targetId)
			.nickname("nickname")
			.position("developer")
			.build();
		Target target = TargetEntityMapper.toTarget(targetEntity);

		targetFindJpaRepository.saveAndFlush(targetEntity);
		entityManager.clear();

		Optional<Target> resultTarget = targetFindAdaptor.findTargetById(targetId);

		assertTrue(resultTarget.isPresent());
		assertEquals(targetId, resultTarget.get().getId());
	}

	@Test
	@DisplayName("targetId로 target 조회 실패테스트")
	void FIND_TARGET_BY_TARGET_ID_FAIL_TEST() {

		Long targetId = 1L;

		Optional<Target> resultTarget = targetFindAdaptor.findTargetById(targetId);

		assertTrue(resultTarget.isEmpty());

	}

}
