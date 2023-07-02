package me.nalab.survey.jpa.adaptor.createfeedback;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.util.Optional;

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
import me.nalab.survey.application.port.out.persistence.createfeedback.SurveyFindPort;
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
	private TestSurveyJpaRepository testSurveyJpaRepository;

	@Autowired
	private TestTargetJpaRepository testTargetJpaRepository;

	@Autowired
	private TestFeedbackJpaRepository testFeedbackJpaRepository;

	@Test
	@DisplayName("Survey 조회 성공 테스트")
	void FIND_SURVEY_SUCCESS() {
		// given
		TargetEntity targetEntity = getDefaultTargetEntity();
		Survey expectedSurvey = RandomSurveyFixture.createRandomSurvey();
		SurveyEntity surveyEntity = SurveyEntityMapper.toSurveyEntity(targetEntity.getId(), expectedSurvey);

		testTargetJpaRepository.saveAndFlush(targetEntity);
		testSurveyJpaRepository.saveAndFlush(surveyEntity);

		// when
		Optional<Survey> result = surveyFindPort.findSurveyBySurveyId(expectedSurvey.getId());

		// then
		assertTrue(result.isPresent());
		assertEquals(expectedSurvey, result.get());
	}

	@Test
	@DisplayName("Survey 조회 성공 테스트 - Survey가 없을 때")
	void FIND_SURVEY_SUCCESS_EMPTY(){
		// when
		Optional<Survey> result = surveyFindPort.findSurveyBySurveyId(1L);

		// then
		assertTrue(result.isEmpty());
	}

	private TargetEntity getDefaultTargetEntity() {
		return TargetEntity.builder()
			.id(101L)
			.createdAt(Instant.now())
			.updatedAt(Instant.now())
			.nickname("test target")
			.build();
	}

}
