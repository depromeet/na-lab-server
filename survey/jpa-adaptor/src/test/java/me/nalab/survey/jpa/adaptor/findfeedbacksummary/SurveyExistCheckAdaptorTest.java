package me.nalab.survey.jpa.adaptor.findfeedbacksummary;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

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
import me.nalab.survey.application.port.out.persistence.feedbacksummary.SurveyExistCheckPort;
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
	private SurveyExistCheckPort surveyExistCheckPort;

	@Autowired
	private TestSurveyJpaRepository testSurveyJpaRepository;

	@Autowired
	private TestTargetJpaRepository testTargetJpaRepository;

	@Test
	@DisplayName("survey exist check 성공 - survey가 존재할때")
	void SURVEY_EXIST_CHECK_SUCCESS() {
		// given
		TargetEntity targetEntity = getDefaultTargetEntity();
		Survey survey = RandomSurveyFixture.createRandomSurvey();
		SurveyEntity surveyEntity = SurveyEntityMapper.toSurveyEntity(targetEntity.getId(), survey);

		testTargetJpaRepository.saveAndFlush(targetEntity);
		testSurveyJpaRepository.saveAndFlush(surveyEntity);
		testSurveyJpaRepository.flush();

		// when
		boolean result = surveyExistCheckPort.isExistSurveyBySurveyId(survey.getId());

		// then
		assertTrue(result);
	}

	@Test
	@DisplayName("survey exist check 성공 - survey가 없을때")
	void SURVEY_EXIST_CHECK_SUCCESS_EMPTY_SURVEY() {
		// when
		boolean result = surveyExistCheckPort.isExistSurveyBySurveyId(100L);

		// then
		assertFalse(result);
	}

	private TargetEntity getDefaultTargetEntity() {
		return TargetEntity.builder()
			.id(101L)
			.createdAt(LocalDateTime.now())
			.updatedAt(LocalDateTime.now())
			.nickname("test target")
			.build();
	}

}
