package me.nalab.survey.jpa.adaptor.find;

import static me.nalab.survey.jpa.adaptor.RandomSurveyFixture.createRandomSurvey;
import static org.junit.jupiter.api.Assertions.*;

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
import me.nalab.survey.domain.survey.Survey;
import me.nalab.survey.jpa.adaptor.common.mapper.SurveyEntityMapper;

@DataJpaTest
@EnableJpaRepositories
@EntityScan("me.nalab.core.data")
@ContextConfiguration(classes = SurveyFindAdaptor.class)
@TestPropertySource("classpath:h2.properties")
class SurveyFindAdaptorTest {

	@Autowired
	private SurveyFindAdaptor surveyFindAdaptor;

	@Autowired
	private TestSurveyFindRepository testSurveyFindRepository;

	@Autowired
	private EntityManager entityManager;

	@Test
	void FIND_SURVEY_TEST() {

		Long targetId = 1L;
		Survey randomSurvey = createRandomSurvey();
		Long surveyId = randomSurvey.getId();
		SurveyEntity surveyEntity = SurveyEntityMapper.toSurveyEntity(targetId, randomSurvey);

		testSurveyFindRepository.saveAndFlush(surveyEntity);
		entityManager.clear();

		Optional<Survey> result = surveyFindAdaptor.findSurvey(surveyId);

		assertTrue(result.isPresent());
		assertEquals(surveyId, result.get().getId());
	}

	@Test
	void FIND_SURVEY_FAIL_TEST() {

		Long surveyId = 1L;

		Optional<Survey> result = surveyFindAdaptor.findSurvey(surveyId);

		assertTrue(result.isEmpty());
	}
}
