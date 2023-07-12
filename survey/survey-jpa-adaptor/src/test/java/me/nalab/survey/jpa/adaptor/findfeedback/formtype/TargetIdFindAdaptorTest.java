package me.nalab.survey.jpa.adaptor.findfeedback.formtype;

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

import me.nalab.core.data.survey.SurveyEntity;
import me.nalab.core.data.target.TargetEntity;
import me.nalab.survey.domain.survey.Survey;
import me.nalab.survey.jpa.adaptor.RandomSurveyFixture;
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
	private TestTargetRepository testTargetRepository;

	@Autowired
	private TestSurveyFindRepository testSurveyFindRepository;

	@Autowired
	private EntityManager entityManager;

	@Test
	@DisplayName("surveyId에 해당하는 targetId 조회 - targetId가 존재하는 경우")
	void FIND_TARGET_ID_WITH_SURVEY_ID_WITH_TARGETID() {

		TargetEntity targetEntity = getTargetEntity();
		Survey survey = RandomSurveyFixture.createRandomSurvey();
		SurveyEntity surveyEntity = SurveyEntityMapper.toSurveyEntity(targetEntity.getId(), survey);

		testTargetRepository.saveAndFlush(targetEntity);
		testSurveyFindRepository.saveAndFlush(surveyEntity);
		entityManager.clear();

		Optional<Long> targetIdBySurveyId = targetIdFindAdaptor.findTargetIdBySurveyId(survey.getId());

		assertFalse(targetIdBySurveyId.isEmpty());
		assertEquals(targetEntity.getId(), targetIdBySurveyId.get());
	}

	@Test
	@DisplayName("surveyId에 해당하는 targetId 조회 - targetId가 존재하지 않는 경우")
	void FIND_TARGET_ID_WITH_SURVEY_ID_WITH_NO_TARGETID() {

		Survey survey = RandomSurveyFixture.createRandomSurvey();

		Optional<Long> targetIdBySurveyId = targetIdFindAdaptor.findTargetIdBySurveyId(survey.getId());

		assertTrue(targetIdBySurveyId.isEmpty());

	}

	private TargetEntity getTargetEntity() {
		return TargetEntity.builder()
			.id(1L)
			.createdAt(Instant.now())
			.updatedAt(Instant.now())
			.nickname("nalab")
			.build();
	}

}
