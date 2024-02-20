package me.nalab.survey.jpa.adaptor.findfeedback.formtype;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;
import java.util.List;

import javax.persistence.EntityManager;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import me.nalab.core.data.feedback.FeedbackEntity;
import me.nalab.core.data.survey.SurveyEntity;
import me.nalab.core.data.target.TargetEntity;
import me.nalab.survey.domain.feedback.Feedback;
import me.nalab.survey.domain.survey.Survey;
import me.nalab.survey.jpa.adaptor.RandomFeedbackFixture;
import me.nalab.survey.jpa.adaptor.RandomSurveyFixture;
import me.nalab.survey.jpa.adaptor.common.mapper.FeedbackEntityMapper;
import me.nalab.survey.jpa.adaptor.common.mapper.SurveyEntityMapper;

@DataJpaTest
@EnableJpaRepositories
@EntityScan("me.nalab.core.data")
@ContextConfiguration(classes = FeedbackFindAdaptor.class)
@TestPropertySource("classpath:h2.properties")
class FeedbackFindAdaptorTest {

	@Autowired
	private FeedbackFindAdaptor feedbackFindAdaptor;

	@Autowired
	private TestTargetRepository testTargetRepository;

	@Autowired
	private TestSurveyFindRepository testSurveyFindRepository;

	@Autowired
	private TestFeedbackFindRepository testFeedbackFindRepository;

	@Autowired
	private EntityManager entityManager;

	@Test
	@DisplayName("surveyId에 해당하는 모든 feedback 조회 - 피드백이 있는 경우")
	void FIND_ALL_FEEDBACK_WITH_SURVEY_ID_WITH_FEEDBACK() {

		TargetEntity targetEntity = getTargetEntity();
		Survey survey = RandomSurveyFixture.createRandomSurvey();
		SurveyEntity surveyEntity = SurveyEntityMapper.toSurveyEntity(targetEntity.getId(), survey);
		FeedbackEntity feedbackEntity = FeedbackEntityMapper.toEntity(
			RandomFeedbackFixture.getRandomFeedbackBySurvey(survey));
		Feedback feedback = FeedbackEntityMapper.toDomain(feedbackEntity);

		testTargetRepository.saveAndFlush(targetEntity);
		testSurveyFindRepository.saveAndFlush(surveyEntity);
		testFeedbackFindRepository.saveAndFlush(feedbackEntity);
		entityManager.clear();

		List<Feedback> feedbackList = feedbackFindAdaptor.findFeedbackBySurveyId(survey.getId());

		assertIterableEquals(List.of(feedback), feedbackList);
	}

	@Test
	@DisplayName("surveyId에 해당하는 모든 feedback 조회 - 피드백이 없는 경우")
	void FIND_ALL_FEEDBACK_WITH_SURVEY_ID_WITH_NO_FEEDBACK() {

		TargetEntity targetEntity = getTargetEntity();
		Survey survey = RandomSurveyFixture.createRandomSurvey();
		SurveyEntity surveyEntity = SurveyEntityMapper.toSurveyEntity(targetEntity.getId(), survey);

		testTargetRepository.saveAndFlush(targetEntity);
		testSurveyFindRepository.saveAndFlush(surveyEntity);
		entityManager.clear();

		List<Feedback> feedbackList = feedbackFindAdaptor.findFeedbackBySurveyId(survey.getId());

		assertEquals(List.of(), feedbackList);
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
