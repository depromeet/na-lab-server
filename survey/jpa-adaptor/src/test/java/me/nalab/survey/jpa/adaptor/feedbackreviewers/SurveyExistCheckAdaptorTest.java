package me.nalab.survey.jpa.adaptor.feedbackreviewers;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

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
@ContextConfiguration(classes = SurveyExistCheckAdaptor.class)
@TestPropertySource("classpath:h2.properties")
class SurveyExistCheckAdaptorTest {

	@Autowired
	private SurveyExistCheckAdaptor surveyExistCheckAdaptor;

	@Autowired
	private TestSurveyJpaRepository testSurveyJpaRepository;

	@Autowired
	private TestTargetJpaRepository testTargetJpaRepository;

	@Test
	void SURVEY_EXIST_CHECK_SUCCESS() {
		// given
		TargetEntity targetEntity = TargetEntity.builder()
			.id(1L)
			.createdAt(LocalDateTime.now())
			.updatedAt(LocalDateTime.now())
			.nickname("target")
			.build();
		Survey survey = RandomSurveyFixture.createRandomSurvey();
		SurveyEntity surveyEntity = SurveyEntityMapper.toSurveyEntity(targetEntity.getId(), survey);

		testTargetJpaRepository.save(targetEntity);
		testSurveyJpaRepository.save(surveyEntity);

		// when
		boolean result = surveyExistCheckAdaptor.isExistSurveyBySurveyId(survey.getId());

		// then
		assertTrue(result);
	}
}
