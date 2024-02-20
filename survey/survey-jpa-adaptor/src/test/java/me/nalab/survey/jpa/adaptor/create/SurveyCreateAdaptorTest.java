package me.nalab.survey.jpa.adaptor.create;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;

import me.nalab.survey.jpa.adaptor.TestTimeUtil;
import org.junit.jupiter.api.BeforeEach;
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
@ContextConfiguration(classes = SurveyCreateAdaptor.class)
@TestPropertySource("classpath:h2.properties")
class SurveyCreateAdaptorTest {

	@Autowired
	private SurveyCreateAdaptor surveyCreateAdaptor;

	@Autowired
	private TestSurveyJpaRepository testSurveyJpaRepository;

	@Autowired
	private TestTargetJpaRepository testTargetJpaRepository;

	@Autowired
	private EntityManager entityManager;

	@BeforeEach
	void OPTIMIZE_RANDOM_FIXTURE() {
		RandomSurveyFixture.initGenerator();
		RandomSurveyFixture.setRandomQuestionCountGenerator(() -> 2);
		RandomSurveyFixture.setRandomChoiceCountGenerator(() -> 2);
	}

	@Test
	@DisplayName("Survey 생성 성공 테스트")
	void SURVEY_PERSISTENCE_SUCCESS() {
		// given
		TargetEntity targetEntity = TargetEntity.builder()
			.id(101L)
			.createdAt(TestTimeUtil.now())
			.updatedAt(TestTimeUtil.now())
			.nickname("test target")
			.build();
		Survey survey = RandomSurveyFixture.createRandomSurvey();

		// when
		testTargetJpaRepository.save(targetEntity);
		surveyCreateAdaptor.createSurvey(targetEntity.getId(), survey);

		entityManager.flush();
		entityManager.clear();

		SurveyEntity created = testSurveyJpaRepository.findById(survey.getId()).orElse(null);

		// then
		assertNotNull(created);
		assertEquals(survey, SurveyEntityMapper.toSurvey(created));
	}

}
