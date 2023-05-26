package me.nalab.survey.jpa.adaptor.findspecific;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import me.nalab.core.data.survey.SurveyEntity;
import me.nalab.survey.application.port.out.persistence.findspecific.SurveyFindPort;
import me.nalab.survey.domain.survey.Survey;
import me.nalab.survey.jpa.adaptor.RandomSurveyFixture;
import me.nalab.survey.jpa.adaptor.common.mapper.SurveyEntityMapper;

@DataJpaTest
@EnableJpaRepositories
@EntityScan("me.nalab.core.data")
@ContextConfiguration(classes = SurveyFindAdaptor.class)
@TestPropertySource("classpath:h2.properties")
class SurveyFindAdaptorTest {

	@Autowired
	private SurveyFindPort surveyFindPort;

	@Autowired
	private TestSurveyFindJpaRepository testSurveyFindJpaRepository;

	@Test
	void FIND_SURVEY_WITH_SUCCESS() {

		Survey survey = RandomSurveyFixture.createRandomSurvey();
		Long surveyId = survey.getId();
		SurveyEntity surveyEntity = SurveyEntityMapper.toSurveyEntity(surveyId, survey);
		testSurveyFindJpaRepository.save(surveyEntity);

		Optional<Survey> resultSurvey = surveyFindPort.findSurvey(surveyId);

		assertTrue(resultSurvey.isPresent());
		assertEquals(survey, resultSurvey.get());
	}

	@Test
	void FIND_SURVEY_WITH_FAIL() {

		Long nonExistentSurveyId = 999L;

		Optional<Survey> resultSurvey = surveyFindPort.findSurvey(nonExistentSurveyId);

		assertTrue(resultSurvey.isEmpty());
	}

	@Test
	void FIND_TARGET_ID_BY_SURVEY_ID_WITH_SUCCESS() {
		// given
		Survey survey = RandomSurveyFixture.createRandomSurvey();
		Long surveyId = survey.getId();
		SurveyEntity surveyEntity = SurveyEntityMapper.toSurveyEntity(surveyId, survey);
		testSurveyFindJpaRepository.save(surveyEntity);

		Optional<Long> resultSurvey = surveyFindPort.findTargetIdBySurveyId(survey.getId());

		// then
		assertTrue(resultSurvey.isPresent());
		assertEquals(surveyEntity.getTargetId(), resultSurvey.get());
	}

	@Test
	void FIND_TARGET_ID_BY_SURVEY_ID_WITH_FAIL() {

		Long nonExistentSurveyId = 999L;

		Optional<Long> result = surveyFindPort.findTargetIdBySurveyId(nonExistentSurveyId);

		assertTrue(result.isEmpty());
	}
}
