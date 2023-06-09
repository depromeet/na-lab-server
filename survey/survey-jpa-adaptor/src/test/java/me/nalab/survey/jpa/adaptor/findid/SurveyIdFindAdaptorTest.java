package me.nalab.survey.jpa.adaptor.findid;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
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
		Long targetId = 123L;
		TargetEntity targetEntity = TargetEntity.builder()
			.id(targetId)
			.nickname("james")
			.createdAt(Instant.now())
			.updatedAt(Instant.now())
			.build();
		SurveyEntity surveyEntity = SurveyEntityMapper.toSurveyEntity(targetId,
			RandomSurveyFixture.createRandomSurvey());

		testTargetJpaRepository.save(targetEntity);
		testSurveyJpaRepository.save(surveyEntity);

		entityManager.flush();
		entityManager.clear();
		// when

		List<Long> surveyIdList = surveyIdFindPort.findAllSurveyIdByTargetId(targetId);

		// then
		assertSurveyId(surveyIdList, surveyEntity);
	}

	@Test
	@DisplayName("Survey Id 조회 성공 테스트 - survey Id가 여러개")
	void FIND_SURVEY_ID_SUCCESS_MULTIPLE_SURVEY_ID() {
		// given
		Long targetId = 123L;
		TargetEntity targetEntity = TargetEntity.builder()
			.id(targetId)
			.nickname("james")
			.createdAt(Instant.now())
			.updatedAt(Instant.now())
			.build();
		List<SurveyEntity> surveyEntityList = new ArrayList<>();
		for(int i = 0; i < 5; i++) {
			surveyEntityList.add(SurveyEntityMapper.toSurveyEntity(targetId, RandomSurveyFixture.createRandomSurvey()));
		}

		testTargetJpaRepository.save(targetEntity);
		testSurveyJpaRepository.saveAll(surveyEntityList);

		entityManager.flush();
		entityManager.clear();
		// when

		List<Long> surveyIdList = surveyIdFindPort.findAllSurveyIdByTargetId(targetId);

		// then
		assertSurveyId(surveyIdList, surveyEntityList.toArray(new SurveyEntity[] {}));
	}

	@Test
	@DisplayName("Survey Id 조회 성공 테스트 - 어떤 surveyId도 찾을 수 없음")
	void FIND_SURVEY_ID_SUCCESS_EMPTY_SURVEY_LIST() {
		// given
		Long targetId = 123L;
		TargetEntity targetEntity = TargetEntity.builder()
			.id(targetId)
			.nickname("james")
			.createdAt(Instant.now())
			.updatedAt(Instant.now())
			.build();

		testTargetJpaRepository.save(targetEntity);

		entityManager.flush();
		entityManager.clear();

		// when

		List<Long> surveyIdList = surveyIdFindPort.findAllSurveyIdByTargetId(targetId);

		// then
		assertSurveyId(surveyIdList);
	}

	private void assertSurveyId(List<Long> expectedSurveyIdList, SurveyEntity... resultEntities) {
		assertEquals(expectedSurveyIdList.size(), resultEntities.length);

		Set<Long> resultLongSet = Stream.of(resultEntities).map(SurveyEntity::getId).collect(Collectors.toSet());

		expectedSurveyIdList.forEach(e -> assertTrue(resultLongSet.contains(e)));
	}

}
