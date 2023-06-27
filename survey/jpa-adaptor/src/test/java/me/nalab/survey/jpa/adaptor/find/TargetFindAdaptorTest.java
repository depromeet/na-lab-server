package me.nalab.survey.jpa.adaptor.find;

import static me.nalab.survey.jpa.adaptor.RandomSurveyFixture.createRandomSurvey;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.util.Optional;
import java.util.stream.LongStream;

import javax.persistence.EntityManager;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import me.nalab.core.data.survey.SurveyEntity;
import me.nalab.core.data.target.TargetEntity;
import me.nalab.survey.domain.survey.Survey;
import me.nalab.survey.domain.target.Target;
import me.nalab.survey.jpa.adaptor.common.mapper.SurveyEntityMapper;

@DataJpaTest
@EnableJpaRepositories
@EntityScan("me.nalab.core.data")
@ContextConfiguration(classes = TargetFindAdaptor.class)
@TestPropertySource("classpath:h2.properties")
class TargetFindAdaptorTest {

	@Autowired
	private TargetFindAdaptor targetFindAdaptor;

	@Autowired
	private TestSurveyFindRepository testSurveyFindRepository;

	@Autowired
	private TestTargetFindRepository testTargetFindRepository;

	@Autowired
	private EntityManager entityManager;

	@Test
	void FIND_TARGET_TEST() {

		Long targetId = 1L;
		TargetEntity targetEntity = TargetEntity.builder()
			.id(targetId)
			.createdAt(Instant.now())
			.updatedAt(Instant.now())
			.nickname("sujin")
			.build();

		testTargetFindRepository.saveAndFlush(targetEntity);
		entityManager.clear();

		Optional<Target> result = targetFindAdaptor.findTarget(targetId);

		assertTrue(result.isPresent());
		assertEquals(targetEntity.getId(), result.get().getId());
	}

	@Test
	void FIND_TARGET_FAIL_TEST() {

		Long targetId = 1L;

		Optional<Target> result = targetFindAdaptor.findTarget(targetId);

		assertTrue(result.isEmpty());
	}

	@Test
	void FIND_TARGET_ID_BY_SURVEY_ID_TEST() {

		Long targetId = 1L;
		Survey randomSurvey = createRandomSurvey();
		Long surveyId = randomSurvey.getId();
		SurveyEntity surveyEntity = SurveyEntityMapper.toSurveyEntity(targetId, randomSurvey);

		testSurveyFindRepository.saveAndFlush(surveyEntity);
		entityManager.clear();

		Optional<Long> result = targetFindAdaptor.findTargetIdBySurveyId(surveyId);

		assertTrue(result.isPresent());
		assertEquals(targetId, result.get());
	}

	@Test
	@DisplayName("저장된 값 없을 때 조회 테스트")
	void FIND_TARGET_BY_USERNAME_TEST_EMPTY_RESULT() {
		// given
		var username = "username";

		// when
		var result = targetFindAdaptor.findAllByUsername(username);

		// then
		Assertions.assertThat(result).isEmpty();
	}

	@Test
	@DisplayName("저장된 값 1개 일때 조회 테스트")
	void FIND_TARGET_BY_USERNAME_TEST_ONE_RESULT() {
		// given
		var username = "username";
		var expectedId = 1L;
		var targetEntity = TargetEntity.builder()
			.id(expectedId)
			.nickname(username)
			.createdAt(Instant.now())
			.updatedAt(Instant.now())
			.build();
		entityManager.persist(targetEntity);
		entityManager.flush();

		// when
		var result = targetFindAdaptor.findAllByUsername(username);

		// then
		Assertions.assertThat(result).isNotEmpty();
		var foundTarget = result.get(0);
		Assertions.assertThat(foundTarget.getId()).isEqualTo(expectedId);
		Assertions.assertThat(foundTarget.getNickname()).isEqualTo(username);
	}

	@Test
	@DisplayName("저장된 값 2개 이상 일때 조회 테스트")
	void FIND_TARGET_BY_USERNAME_TEST_MORE_RESULT() {
		// given
		var username = "username";
		var expectedSize = 10;
		LongStream.range(0, expectedSize).forEach(id -> {
			var targetEntity = TargetEntity.builder()
				.id(id)
				.nickname(username)
				.createdAt(Instant.now())
				.updatedAt(Instant.now())
				.build();

			entityManager.persist(targetEntity);
		});
		entityManager.flush();

		// when
		var result = targetFindAdaptor.findAllByUsername(username);

		// then
		Assertions.assertThat(result).hasSize(expectedSize);
	}

	@Test
	void FIND_TARGET_ID_BY_SURVEY_ID_FAIL_TEST() {

		Long surveyId = 1L;

		Optional<Long> result = targetFindAdaptor.findTargetIdBySurveyId(surveyId);

		assertTrue(result.isEmpty());
	}
}
