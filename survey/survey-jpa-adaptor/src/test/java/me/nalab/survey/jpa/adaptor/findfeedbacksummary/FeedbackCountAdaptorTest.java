package me.nalab.survey.jpa.adaptor.findfeedbacksummary;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import me.nalab.core.data.feedback.FeedbackEntity;
import me.nalab.core.data.feedback.ReviewerEntity;

@DataJpaTest
@EnableJpaRepositories
@EntityScan("me.nalab.core.data")
@ContextConfiguration(classes = FeedbackCountAdaptor.class)
@TestPropertySource("classpath:h2.properties")
class FeedbackCountAdaptorTest {

	@Autowired
	private FeedbackCountAdaptor feedbackCountAdaptor;

	@Autowired
	private TestReviewerSaveRepository testReviewerSaveRepository;

	@Autowired
	private TestFeedbackCountJpaRepository testFeedbackCountJpaRepository;

	@Autowired
	private EntityManager entityManager;

	@Test
	@DisplayName("전체 조회 - 질문 폼에 해당하는 피드백이 없는 경우")
	void FIND_TOTAL_FEEDBACK_COUNT_TEST_WITH_NO_FEEDBACK() {

		Long surveyId = 1L;
		long resultCount = feedbackCountAdaptor.getTotalFeedbackCountBySurveyId(surveyId);
		assertEquals(0, resultCount);
	}

	@Test
	@DisplayName("전체 조회 - 질문 폼에 해당하는 피드백이 있는 경우")
	void FIND_TOTAL_FEEDBACK_COUNT_TEST_WITH_FEEDBACKS() {

		Long surveyId = 1L;
		createAndSaveSurveyWithFeedback(surveyId);

		long resultCount = feedbackCountAdaptor.getTotalFeedbackCountBySurveyId(surveyId);

		assertEquals(2L, resultCount);
	}

	@Test
	@DisplayName("읽지 않은 피드백 조회 - 질문 폼에 해당하는 피드백이 없는 경우")
	void FIND_UPDATED_FEEDBACK_COUNT_TEST_WITH_NO_FEEDBACK() {

		Long surveyId = 1L;
		long resultCount = feedbackCountAdaptor.getUpdatedFeedbackCountBySurveyId(surveyId);
		assertEquals(0, resultCount);
	}

	@Test
	@DisplayName("읽지 않은 피드백 조회 - 질문 폼에 해당하는 읽지 않은 피드백이 없는 경우")
	void FIND_UPDATED_FEEDBACK_COUNT_TEST_WITH_ALL_READ() {

		Long surveyId = 1L;
		createAndSaveSurveyWithFeedback(surveyId);

		long resultCount = feedbackCountAdaptor.getUpdatedFeedbackCountBySurveyId(surveyId);

		assertEquals(0, resultCount);
	}

	@Test
	@DisplayName("읽지 않은 피드백 조회 - 질문 폼에 해당하는 읽지 않은 피드백이 존재하는 경우")
	void FIND_UPDATED_FEEDBACK_COUNT_TEST_WITH_NOT_READ() {
		Long surveyId = 1L;
		createAndSaveSurveyWithUpdatedFeedback(surveyId);

		long resultCount = feedbackCountAdaptor.getUpdatedFeedbackCountBySurveyId(surveyId);

		assertEquals(1L, resultCount);
	}

	private void createAndSaveSurveyWithFeedback(Long surveyId) {
		ReviewerEntity reviewerEntity1 = ReviewerEntity.builder()
			.id(1L)
			.collaborationExperience(true)
			.position("developer")
			.nickName("su jin")
			.createdAt(Instant.now())
			.updatedAt(Instant.now())
			.build();
		ReviewerEntity reviewerEntity2 = ReviewerEntity.builder()
			.id(2L)
			.collaborationExperience(true)
			.position("developer")
			.nickName("jun young")
			.createdAt(Instant.now())
			.updatedAt(Instant.now())
			.build();
		FeedbackEntity feedbackEntity1 = FeedbackEntity.builder()
			.id(1L)
			.surveyId(surveyId)
			.isRead(true)
			.reviewer(reviewerEntity1)
			.createdAt(Instant.now())
			.updatedAt(Instant.now())
			.formFeedbackEntityList(List.of())
			.build();
		FeedbackEntity feedbackEntity2 = FeedbackEntity.builder()
			.id(2L)
			.surveyId(surveyId)
			.isRead(true)
			.reviewer(reviewerEntity2)
			.createdAt(Instant.now())
			.updatedAt(Instant.now())
			.formFeedbackEntityList(List.of())
			.build();

		testReviewerSaveRepository.saveAndFlush(reviewerEntity1);
		testReviewerSaveRepository.saveAndFlush(reviewerEntity2);
		testFeedbackCountJpaRepository.saveAndFlush(feedbackEntity1);
		testFeedbackCountJpaRepository.saveAndFlush(feedbackEntity2);

		entityManager.clear();
	}

	private void createAndSaveSurveyWithUpdatedFeedback(Long surveyId) {
		ReviewerEntity reviewerEntity1 = ReviewerEntity.builder()
			.id(1L)
			.collaborationExperience(true)
			.position("developer")
			.nickName("su jin")
			.createdAt(Instant.now())
			.updatedAt(Instant.now())
			.build();
		ReviewerEntity reviewerEntity2 = ReviewerEntity.builder()
			.id(2L)
			.collaborationExperience(true)
			.position("developer")
			.nickName("jun young")
			.createdAt(Instant.now())
			.updatedAt(Instant.now())
			.build();
		ReviewerEntity reviewerEntity3 = ReviewerEntity.builder()
			.id(3L)
			.collaborationExperience(true)
			.position("developer")
			.nickName("do jin")
			.createdAt(Instant.now())
			.updatedAt(Instant.now())
			.build();
		FeedbackEntity feedbackEntity1 = FeedbackEntity.builder()
			.id(1L)
			.surveyId(surveyId)
			.isRead(true)
			.reviewer(reviewerEntity1)
			.createdAt(Instant.now())
			.updatedAt(Instant.now())
			.formFeedbackEntityList(List.of())
			.build();
		FeedbackEntity feedbackEntity2 = FeedbackEntity.builder()
			.id(2L)
			.surveyId(surveyId)
			.isRead(true)
			.reviewer(reviewerEntity2)
			.createdAt(Instant.now())
			.updatedAt(Instant.now())
			.formFeedbackEntityList(List.of())
			.build();
		FeedbackEntity feedbackEntity3 = FeedbackEntity.builder()
			.id(3L)
			.surveyId(surveyId)
			.isRead(false)
			.reviewer(reviewerEntity3)
			.createdAt(Instant.now())
			.updatedAt(Instant.now())
			.formFeedbackEntityList(List.of())
			.build();

		testReviewerSaveRepository.saveAndFlush(reviewerEntity1);
		testReviewerSaveRepository.saveAndFlush(reviewerEntity2);
		testReviewerSaveRepository.saveAndFlush(reviewerEntity3);
		testFeedbackCountJpaRepository.saveAndFlush(feedbackEntity1);
		testFeedbackCountJpaRepository.saveAndFlush(feedbackEntity2);
		testFeedbackCountJpaRepository.saveAndFlush(feedbackEntity3);

		entityManager.clear();
	}
}
