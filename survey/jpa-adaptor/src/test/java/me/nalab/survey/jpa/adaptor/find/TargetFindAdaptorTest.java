package me.nalab.survey.jpa.adaptor.find;

import static me.nalab.survey.jpa.adaptor.RandomSurveyFixture.createRandomSurvey;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;
import java.util.Optional;

import javax.persistence.EntityManager;

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
	void FIND_TARGET_ID_BY_SURVEY_ID_FAIL_TEST() {

		Long surveyId = 1L;

		Optional<Long> result = targetFindAdaptor.findTargetIdBySurveyId(surveyId);

		assertTrue(result.isEmpty());
	}
}
