package me.nalab.survey.jpa.adaptor.findfeedback;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

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
import me.nalab.survey.application.port.out.persistence.findfeedback.FeedbackFindPort;
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
	private FeedbackFindPort feedbackFindPort;

	@Autowired
	private TestFeedbackJpaRepository testFeedbackJpaRepository;

	@Autowired
	private TestSurveyJpaRepository testSurveyJpaRepository;

	@Autowired
	private TestTargetJpaRepository testTargetJpaRepository;

	@Test
	@DisplayName("Feedback 조회 성공 테스트")
	void FIND_FEEDBACK_LIST_SUCCESS() {
		// given
		TargetEntity targetEntity = getDefaultTargetEntity();
		Survey survey = RandomSurveyFixture.createRandomSurvey();
		SurveyEntity surveyEntity = SurveyEntityMapper.toSurveyEntity(targetEntity.getId(), survey);
		FeedbackEntity feedbackEntity = FeedbackEntityMapper.toEntity(
			RandomFeedbackFixture.getRandomFeedbackBySurvey(survey));
		List<Feedback> expected = List.of(FeedbackEntityMapper.toDomain(feedbackEntity));

		testTargetJpaRepository.save(targetEntity);
		testSurveyJpaRepository.save(surveyEntity);
		testFeedbackJpaRepository.save(feedbackEntity);

		// when
		List<Feedback> feedbackList = feedbackFindPort.findAllFeedbackBySurveyId(survey.getId());

		// then
		assertIterableEquals(expected, feedbackList);
	}

	@Test
	@DisplayName("Feedback 조회 성공 테스트 - Empty feedback list")
	void FIND_FEEDBACK_LIST_SUCCESS_EMPTY_FEEDBACK() {
		// given
		TargetEntity targetEntity = getDefaultTargetEntity();
		Survey survey = RandomSurveyFixture.createRandomSurvey();
		SurveyEntity surveyEntity = SurveyEntityMapper.toSurveyEntity(targetEntity.getId(), survey);

		testTargetJpaRepository.save(targetEntity);
		testSurveyJpaRepository.save(surveyEntity);

		// when
		List<Feedback> feedbackList = feedbackFindPort.findAllFeedbackBySurveyId(survey.getId());

		// then
		assertTrue(feedbackList.isEmpty());
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
