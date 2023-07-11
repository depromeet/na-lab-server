package me.nalab.survey.jpa.adaptor.findtarget;

import static me.nalab.survey.jpa.adaptor.RandomSurveyFixture.*;
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

import me.nalab.core.data.survey.SurveyEntity;
import me.nalab.survey.domain.survey.Survey;
import me.nalab.survey.jpa.adaptor.common.mapper.SurveyEntityMapper;

@DataJpaTest
@EnableJpaRepositories
@EntityScan("me.nalab.core.data")
@ContextConfiguration(classes = TargetIdFindAdaptor.class)
@TestPropertySource("classpath:h2.properties")
class TargetIdFindAdaptorTest {

	@Autowired
	private TargetIdFindAdaptor targetIdFindAdaptor;

	@Autowired
	private TestTargetIdFindJpaRepository targetIdFindJpaRepository;

	@Autowired
	private EntityManager entityManager;

	@Test
	@DisplayName("surveyId로 targetId 조회 성공테스트")
	void FIND_TARGET_ID_BY_SURVEY_ID_SUCCESS_TEST() {

		Long targetId = 1L;
		Survey randomSurvey = createRandomSurvey();
		Long surveyId = randomSurvey.getId();
		SurveyEntity surveyEntity = SurveyEntityMapper.toSurveyEntity(targetId, randomSurvey);

		targetIdFindJpaRepository.saveAndFlush(surveyEntity);
		entityManager.clear();

		Optional<Long> targetIdBySurveyId = targetIdFindAdaptor.findTargetIdBySurveyId(surveyId);

		assertTrue(targetIdBySurveyId.isPresent());
		assertEquals(targetId, targetIdBySurveyId.get());

	}

	@Test
	@DisplayName("surveyId로 targetId 조회 실패테스트")
	void FIND_TARGET_ID_BY_SURVEY_ID_FAIL_TEST() {

		Survey randomSurvey = createRandomSurvey();
		Long surveyId = randomSurvey.getId();

		Optional<Long> targetIdBySurveyId = targetIdFindAdaptor.findTargetIdBySurveyId(surveyId);

		assertTrue(targetIdBySurveyId.isEmpty());

	}

}
