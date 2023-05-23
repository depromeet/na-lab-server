package me.nalab.survey.jpa.adaptor.createfeedback;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
import me.nalab.survey.application.port.out.persistence.createfeedback.ReviewerLatestNameFindPort;
import me.nalab.survey.domain.survey.Survey;
import me.nalab.survey.jpa.adaptor.RandomFeedbackFixture;
import me.nalab.survey.jpa.adaptor.RandomSurveyFixture;
import me.nalab.survey.jpa.adaptor.common.mapper.FeedbackEntityMapper;
import me.nalab.survey.jpa.adaptor.common.mapper.SurveyEntityMapper;

@DataJpaTest
@EnableJpaRepositories
@EntityScan("me.nalab.core.data")
@ContextConfiguration(classes = ReviewerLatestNameFindAdaptor.class)
@TestPropertySource("classpath:h2.properties")
class ReviewerLatestNameFindAdaptorTest {

	@Autowired
	private ReviewerLatestNameFindPort reviewerLatestNameFindPort;

	@Autowired
	private TestSurveyJpaRepository testSurveyJpaRepository;

	@Autowired
	private TestTargetJpaRepository testTargetJpaRepository;

	@Autowired
	private TestFeedbackJpaRepository testFeedbackJpaRepository;

	@Test
	@DisplayName("마지막으로 저장된 Reviewer name 조회 성공 테스트 - 리뷰어가 있을때")
	void FIND_LATEST_REVIEWER_NAME_SUCCESS() {
		// given
		TargetEntity targetEntity = getDefaultTargetEntity();
		Survey survey = RandomSurveyFixture.createRandomSurvey();
		SurveyEntity surveyEntity = SurveyEntityMapper.toSurveyEntity(targetEntity.getId(), survey);

		List<FeedbackEntity> feedbackEntityList = List.of(
			FeedbackEntityMapper.toEntity(RandomFeedbackFixture.getRandomFeedbackBySurvey(survey)),
			FeedbackEntityMapper.toEntity(RandomFeedbackFixture.getRandomFeedbackBySurvey(survey)),
			FeedbackEntityMapper.toEntity(RandomFeedbackFixture.getRandomFeedbackBySurvey(survey))
		);

		String expectedReviewerName = getLatestReviewerName(feedbackEntityList);

		testTargetJpaRepository.saveAndFlush(targetEntity);
		testSurveyJpaRepository.saveAndFlush(surveyEntity);
		testFeedbackJpaRepository.saveAll(feedbackEntityList);

		// when
		Optional<String> reviewerName = reviewerLatestNameFindPort.getLatestReviewerNameBySurveyId(survey.getId());

		// then
		assertTrue(reviewerName.isPresent());
		assertEquals(expectedReviewerName, reviewerName.get());
	}

	@Test
	@DisplayName("마지막으로 저장된 Reviewer name 조회 성공 테스트 - 리뷰어가 없을때")
	void FIND_LATEST_REVIEWER_NAME_SUCCESS_FIRST_REVIEWER() {
		// given
		TargetEntity targetEntity = getDefaultTargetEntity();
		Survey survey = RandomSurveyFixture.createRandomSurvey();
		SurveyEntity surveyEntity = SurveyEntityMapper.toSurveyEntity(targetEntity.getId(), survey);

		testTargetJpaRepository.saveAndFlush(targetEntity);
		testSurveyJpaRepository.saveAndFlush(surveyEntity);

		// when
		Optional<String> reviewerName = reviewerLatestNameFindPort.getLatestReviewerNameBySurveyId(survey.getId());

		// then
		assertTrue(reviewerName.isEmpty());
	}

	private TargetEntity getDefaultTargetEntity() {
		return TargetEntity.builder()
			.id(101L)
			.createdAt(LocalDateTime.now())
			.updatedAt(LocalDateTime.now())
			.nickname("test target")
			.build();
	}

	private String getLatestReviewerName(List<FeedbackEntity> feedbackEntityList) {
		LocalDateTime latest = null;
		String latestName = null;
		for(FeedbackEntity feedbackEntity : feedbackEntityList) {
			if(latest == null || feedbackEntity.getCreatedAt().isAfter(latest)) {
				latest = feedbackEntity.getReviewer().getCreatedAt();
				latestName = feedbackEntity.getReviewer().getNickName();
			}
		}
		return latestName;
	}

}
