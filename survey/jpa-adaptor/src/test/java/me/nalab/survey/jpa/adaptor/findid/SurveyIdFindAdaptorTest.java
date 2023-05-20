package me.nalab.survey.jpa.adaptor.findid;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

import javax.persistence.EntityManager;

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
import me.nalab.survey.application.port.out.persistence.findid.SurveyIdFindPort;
import me.nalab.survey.jpa.adaptor.RandomSurveyFixture;
import me.nalab.survey.jpa.adaptor.common.mapper.SurveyEntityMapper;

@DataJpaTest
@EnableJpaRepositories
@EntityScan("me.nalab.core.data")
@ContextConfiguration(classes = SurveyIdFindAdaptor.class)
@TestPropertySource("classpath:h2.properties")
class SurveyIdFindAdaptorTest {

	@Autowired
	private SurveyIdFindPort surveyIdFindPort;

	@Autowired
	private TestSurveyJpaRepository testSurveyJpaRepository;

	@Autowired
	private TestTargetJpaRepository testTargetJpaRepository;

	@Autowired
	private EntityManager entityManager;

	@Test
	@DisplayName("Survey Id 조회 성공 테스트 - Survey Id가 1개")
	void FIND_SURVEY_ID_SUCCESS() {
		// given
		TargetEntity targetEntity = TargetEntity.builder()
			.id(123L)
			.nickname("james")
			.createdAt(LocalDateTime.now())
			.updatedAt(LocalDateTime.now())
			.build();
		SurveyEntity surveyEntity = SurveyEntityMapper.toSurveyEntity(123L, RandomSurveyFixture.createRandomSurvey());

		// when
		testTargetJpaRepository.save(targetEntity);
		testSurveyJpaRepository.save(surveyEntity);

		entityManager.flush();
		entityManager.clear();

		List<Long> surveyIdList = surveyIdFindPort.findSurveyIdByTargetId(123L);

		// then
		assertSurveyId(surveyIdList, surveyEntity);
	}

	@Test
	@DisplayName("Survey Id 조회 성공 테스트 - survey Id가 여러개")
	void FIND_SURVEY_ID_SUCCESS_MULTIPLE_SURVEY_ID() {
		// given
		TargetEntity targetEntity = TargetEntity.builder()
			.id(123L)
			.nickname("james")
			.createdAt(LocalDateTime.now())
			.updatedAt(LocalDateTime.now())
			.build();
		SurveyEntity surveyEntity1 = SurveyEntityMapper.toSurveyEntity(123L, RandomSurveyFixture.createRandomSurvey());
		SurveyEntity surveyEntity2 = SurveyEntityMapper.toSurveyEntity(123L, RandomSurveyFixture.createRandomSurvey());
		SurveyEntity surveyEntity3 = SurveyEntityMapper.toSurveyEntity(123L, RandomSurveyFixture.createRandomSurvey());
		SurveyEntity surveyEntity4 = SurveyEntityMapper.toSurveyEntity(123L, RandomSurveyFixture.createRandomSurvey());
		SurveyEntity surveyEntity5 = SurveyEntityMapper.toSurveyEntity(123L, RandomSurveyFixture.createRandomSurvey());

		// when
		testTargetJpaRepository.save(targetEntity);
		testSurveyJpaRepository.saveAll(
			List.of(surveyEntity1, surveyEntity2, surveyEntity3, surveyEntity4, surveyEntity5));

		entityManager.flush();
		entityManager.clear();

		List<Long> surveyIdList = surveyIdFindPort.findSurveyIdByTargetId(123L);

		// then
		assertSurveyId(surveyIdList, surveyEntity1, surveyEntity2, surveyEntity3, surveyEntity4, surveyEntity5);
	}

	@Test
	@DisplayName("Survey Id 조회 성공 테스트 - 어떤 surveyId도 찾을 수 없음")
	void FIND_SURVEY_ID_SUCCESS_EMPTY_SURVEY_LIST() {
		// given
		TargetEntity targetEntity = TargetEntity.builder()
			.id(123L)
			.nickname("james")
			.createdAt(LocalDateTime.now())
			.updatedAt(LocalDateTime.now())
			.build();

		// when
		testTargetJpaRepository.save(targetEntity);

		entityManager.flush();
		entityManager.clear();

		List<Long> surveyIdList = surveyIdFindPort.findSurveyIdByTargetId(123L);

		// then
		assertSurveyId(surveyIdList);
	}

	private void assertSurveyId(List<Long> expectedSurveyIdList, SurveyEntity... resultEntities) {
		assertEquals(expectedSurveyIdList.size(), resultEntities.length);
		Stream.of(resultEntities).forEach(re -> assertTrue(expectedSurveyIdList.contains(re.getId())));
	}

}
