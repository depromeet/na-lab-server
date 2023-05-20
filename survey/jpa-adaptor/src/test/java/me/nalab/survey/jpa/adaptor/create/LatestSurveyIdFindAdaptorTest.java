package me.nalab.survey.jpa.adaptor.create;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
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
import me.nalab.survey.jpa.adaptor.RandomSurveyFixture;
import me.nalab.survey.jpa.adaptor.common.mapper.SurveyEntityMapper;

@DataJpaTest
@EnableJpaRepositories
@EntityScan("me.nalab.core.data")
@ContextConfiguration(classes = LatestSurveyIdFindAdaptor.class)
@TestPropertySource("classpath:h2.properties")
class LatestSurveyIdFindAdaptorTest {

	@Autowired
	private LatestSurveyIdFindAdaptor latestSurveyIdFindAdaptor;

	@Autowired
	private TestSurveyJpaRepository testSurveyJpaRepository;

	@Autowired
	private TestTargetJpaRepository testTargetJpaRepository;

	@Autowired
	private EntityManager entityManager;

	@BeforeEach
	void OPTIMIZE_RANDOM_FIXTURE() {
		RandomSurveyFixture.initGenerator();
		RandomSurveyFixture.setRandomQuestionCountGenerator(() -> 2);
		RandomSurveyFixture.setRandomChoiceCountGenerator(() -> 2);
	}

	@Test
	@DisplayName("최근 생성된 Survey의 ID 조회 성공 테스트")
	void FIND_SURVEY_ID_SUCCESS() {
		// given
		TargetEntity targetEntity = TargetEntity.builder()
			.id(1L)
			.nickname("Hello")
			.createdAt(LocalDateTime.now())
			.updatedAt(LocalDateTime.now())
			.build();

		SurveyEntity surveyEntity = SurveyEntityMapper.toSurveyEntity(targetEntity.getId(),
			RandomSurveyFixture.createRandomSurvey());

		// when
		testTargetJpaRepository.save(targetEntity);
		testSurveyJpaRepository.save(surveyEntity);

		entityManager.flush();
		entityManager.clear();

		Optional<Long> result = latestSurveyIdFindAdaptor.findLatestSurveyIdByTargetId(targetEntity.getId());

		// then
		assertTrue(result.isPresent());
		assertEquals(surveyEntity.getId(), result.get());
	}

	@Test
	@DisplayName("최근 생성된 Survey의 ID 조회 성공 테스트 - Survey가 2개")
	void FIND_SURVEY_ID_SUCCESS_MULTIPLE_SURVEY() {
		// given
		TargetEntity targetEntity = TargetEntity.builder()
			.id(1L)
			.nickname("Hello")
			.createdAt(LocalDateTime.now())
			.updatedAt(LocalDateTime.now())
			.build();

		RandomSurveyFixture.setRandomDateTimeGenerator(() -> LocalDateTime.now().minusDays(1));
		SurveyEntity pastSurvey = SurveyEntityMapper.toSurveyEntity(targetEntity.getId(),
			RandomSurveyFixture.createRandomSurvey());

		RandomSurveyFixture.setRandomDateTimeGenerator(LocalDateTime::now);
		SurveyEntity latestSurvey = SurveyEntityMapper.toSurveyEntity(targetEntity.getId(),
			RandomSurveyFixture.createRandomSurvey());

		// when
		testTargetJpaRepository.save(targetEntity);
		testSurveyJpaRepository.save(latestSurvey);
		testSurveyJpaRepository.save(pastSurvey);

		entityManager.flush();
		entityManager.clear();

		Optional<Long> result = latestSurveyIdFindAdaptor.findLatestSurveyIdByTargetId(targetEntity.getId());

		// then
		assertTrue(result.isPresent());
		assertEquals(latestSurvey.getId(), result.get());
	}

}
