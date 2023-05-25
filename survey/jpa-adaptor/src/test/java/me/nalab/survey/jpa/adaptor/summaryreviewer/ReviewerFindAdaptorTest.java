package me.nalab.survey.jpa.adaptor.summaryreviewer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
import me.nalab.survey.domain.feedback.Reviewer;
import me.nalab.survey.domain.survey.Survey;
import me.nalab.survey.jpa.adaptor.RandomFeedbackFixture;
import me.nalab.survey.jpa.adaptor.RandomSurveyFixture;
import me.nalab.survey.jpa.adaptor.common.mapper.FeedbackEntityMapper;
import me.nalab.survey.jpa.adaptor.common.mapper.SurveyEntityMapper;

@DataJpaTest
@EnableJpaRepositories
@EntityScan("me.nalab.core.data")
@ContextConfiguration(classes = ReviewerFindAdaptor.class)
@TestPropertySource("classpath:h2.properties")
class ReviewerFindAdaptorTest {

	@Autowired
	private ReviewerFindAdaptor reviewerFindAdaptor;

	@Autowired
	private TestFeedbackJpaRepository testFeedbackJpaRepository;

	@Autowired
	private TestSurveyJpaRepository testSurveyJpaRepository;

	@Autowired
	private TestTargetJpaRepository testTargetJpaRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Test
	@DisplayName("모든 Reviewer 조회 성공 테스트")
	void FIND_ALL_REVIEWER_SUCCESS() {
		// given
		TargetEntity targetEntity = getDefaultTargetEntity();
		Survey survey = RandomSurveyFixture.createRandomSurvey();
		SurveyEntity surveyEntity = SurveyEntityMapper.toSurveyEntity(targetEntity.getId(), survey);
		List<FeedbackEntity> feedbackEntity = List.of(
			FeedbackEntityMapper.toEntity(RandomFeedbackFixture.getRandomFeedbackBySurvey(survey)),
			FeedbackEntityMapper.toEntity(RandomFeedbackFixture.getRandomFeedbackBySurvey(survey)),
			FeedbackEntityMapper.toEntity(RandomFeedbackFixture.getRandomFeedbackBySurvey(survey))
		);

		testTargetJpaRepository.saveAndFlush(targetEntity);
		testSurveyJpaRepository.saveAndFlush(surveyEntity);
		testFeedbackJpaRepository.saveAll(feedbackEntity);
		testSurveyJpaRepository.flush();

		entityManager.clear();

		// when
		List<Reviewer> result = reviewerFindAdaptor.findAllReviewer(survey.getId());

		// then
		assertEquals(feedbackEntity.size(), result.size());
	}

	@Test
	@DisplayName("모든 Reviewer 조회 성공 테스트 - Reviewer가 없을때")
	void FIND_ALL_REVIEWER_SUCCESS_EMPTY_REVIEWER() {
		// given
		TargetEntity targetEntity = getDefaultTargetEntity();
		Survey survey = RandomSurveyFixture.createRandomSurvey();
		SurveyEntity surveyEntity = SurveyEntityMapper.toSurveyEntity(targetEntity.getId(), survey);

		testTargetJpaRepository.saveAndFlush(targetEntity);
		testSurveyJpaRepository.saveAndFlush(surveyEntity);
		testSurveyJpaRepository.flush();

		entityManager.clear();

		// when
		List<Reviewer> result = reviewerFindAdaptor.findAllReviewer(survey.getId());

		// then
		assertTrue(result.isEmpty());
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
