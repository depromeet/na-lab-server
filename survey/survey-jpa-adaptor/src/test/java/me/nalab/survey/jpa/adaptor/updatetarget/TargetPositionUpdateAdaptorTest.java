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
import me.nalab.survey.jpa.adaptor.common.mapper.TargetEntityMapper;

@DataJpaTest
@EnableJpaRepositories
@EntityScan("me.nalab.core.data")
@ContextConfiguration(classes = TargetPositionUpdateAdaptor.class)
@TestPropertySource("classpath:h2.properties")
class TargetPositionUpdateAdaptorTest {

	@Autowired
	private TargetPositionUpdateAdaptor targetPositionUpdateAdaptor;

	@Autowired
	private TestTargetPositionUpdateRepository testTargetPositionUpdateRepository;

	@Autowired
	private EntityManager entityManager;

	@Test
	@DisplayName("타겟 position 수정 성공 테스트")
	void UPDATE_TARGET_POSITION_SUCCESS() {

		Long targetId = 1L;
		TargetEntity targetEntity = TargetEntity.builder()
			.id(targetId)
			.createdAt(Instant.now())
			.updatedAt(Instant.now())
			.nickname("sujin")
			.position("designer")
			.build();
		Target target = TargetEntityMapper.toTarget(targetEntity);
		testTargetPositionUpdateRepository.saveAndFlush(targetEntity);
		entityManager.clear();

		String updatePosition = "developer";
		target.setPosition(updatePosition);
		targetPositionUpdateAdaptor.updateTargetPosition(target);

		Optional<TargetEntity> resultTargetEntity = testTargetPositionUpdateRepository.findById(targetId);
		assertTrue(resultTargetEntity.isPresent());
		assertEquals(updatePosition, resultTargetEntity.get().getPosition());

	}

}
