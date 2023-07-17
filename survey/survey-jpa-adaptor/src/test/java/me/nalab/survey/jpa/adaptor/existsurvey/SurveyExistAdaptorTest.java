package me.nalab.survey.jpa.adaptor.existsurvey;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;

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
@ContextConfiguration(classes = SurveyExistAdaptor.class)
@TestPropertySource("classpath:h2.properties")
class SurveyExistAdaptorTest {

	@Autowired
	private SurveyExistAdaptor surveyExistAdaptor;

	@Autowired
	private TestSurveyFindRepository testSurveyFindRepository;

	@Autowired
	private TestTargetRepository testTargetRepository;

	@Autowired
	private EntityManager entityManager;

	@Test
	@DisplayName("targetID에 해당하는 survey가 존재하는 경우")
	void FIND_SURVEY_WITH_TARGET_ID_WITH_SURVEY() {

		TargetEntity targetEntity = getTargetEntity();
		Survey survey = RandomSurveyFixture.createRandomSurvey();
		SurveyEntity surveyEntity = SurveyEntityMapper.toSurveyEntity(targetEntity.getId(), survey);

		testTargetRepository.saveAndFlush(targetEntity);
		testSurveyFindRepository.saveAndFlush(surveyEntity);
		entityManager.clear();

		boolean result = testSurveyFindRepository.existsByTargetId(targetEntity.getId());
		assertTrue(result);
	}

	@Test
	@DisplayName("targetID에 해당하는 survey가 존재하지 않는 경우")
	void FIND_SURVEY_WITH_TARGET_ID_WITH_NO_SURVEY() {

		TargetEntity targetEntity = getTargetEntity();

		testTargetRepository.saveAndFlush(targetEntity);
		entityManager.clear();

		boolean result = testSurveyFindRepository.existsByTargetId(targetEntity.getId());
		assertFalse(result);
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
