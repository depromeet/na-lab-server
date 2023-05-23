package me.nalab.survey.jpa.adaptor.findfeedbacksummary;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
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
@ContextConfiguration(classes = UpdatedFeedbackCountAdaptor.class)
@TestPropertySource("classpath:h2.properties")
class UpdatedFeedbackCountAdaptorTest {

	@Autowired
	private UpdatedFeedbackCountAdaptor updatedFeedbackCountAdaptor;

	@Autowired
	private TestFeedbackSaveRepository testFeedbackSaveRepository;
	
	@Autowired
	private TestReviewerSaveRepository testReviewerSaveRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	@Test
	@DisplayName("질문 폼에 해당하는 피드백이 없는 경우")
	void FIND_UPDATED_FEEDBACK_COUNT_TEST_WITH_NO_FEEDBACK() {

		Long surveyId = 1L;
		int resultCount = updatedFeedbackCountAdaptor.getUpdatedFeedbackCountBySurveyId(surveyId);
		assertEquals(0, resultCount);
	}

	@Test
	@DisplayName("질문 폼에 해당하는 읽지 않은 피드백이 없는 경우")
	void FIND_UPDATED_FEEDBACK_COUNT_TEST_WITH_ALL_READ() {

		Long surveyId = 1L;
		ReviewerEntity reviewerEntity1 = ReviewerEntity.builder()
			.id(1L)
			.collaborationExperience(true)
			.position("developer")
			.nickName("su jin")
			.createdAt(LocalDateTime.now())
			.updatedAt(LocalDateTime.now())
			.build();
		ReviewerEntity reviewerEntity2 = ReviewerEntity.builder()
			.id(2L)
			.collaborationExperience(true)
			.position("developer")
			.nickName("jun young")
			.createdAt(LocalDateTime.now())
			.updatedAt(LocalDateTime.now())
			.build();
		FeedbackEntity feedbackEntity1 = FeedbackEntity.builder()
			.id(1L)
			.surveyId(surveyId)
			.isRead(true)
			.reviewer(reviewerEntity1)
			.createdAt(LocalDateTime.now())
			.updatedAt(LocalDateTime.now())
			.formFeedbackEntityList(List.of())
			.build();
		FeedbackEntity feedbackEntity2 = FeedbackEntity.builder()
			.id(2L)
			.surveyId(surveyId)
			.isRead(true)
			.reviewer(reviewerEntity2)
			.createdAt(LocalDateTime.now())
			.updatedAt(LocalDateTime.now())
			.formFeedbackEntityList(List.of())
			.build();

		testReviewerSaveRepository.saveAndFlush(reviewerEntity1);
		testReviewerSaveRepository.saveAndFlush(reviewerEntity2);
		testFeedbackSaveRepository.saveAndFlush(feedbackEntity1);
		testFeedbackSaveRepository.saveAndFlush(feedbackEntity2);

		entityManager.clear();

		int resultCount = updatedFeedbackCountAdaptor.getUpdatedFeedbackCountBySurveyId(surveyId);

		assertEquals(0, resultCount);
	}

	@Test
	@DisplayName("질문 폼에 해당하는 읽지 않은 피드백이 존재하는 경우")
	void FIND_UPDATED_FEEDBACK_COUNT_TEST_WITH_NOT_READ() {
		Long surveyId = 1L;
		ReviewerEntity reviewerEntity1 = ReviewerEntity.builder()
			.id(1L)
			.collaborationExperience(true)
			.position("developer")
			.nickName("su jin")
			.createdAt(LocalDateTime.now())
			.updatedAt(LocalDateTime.now())
			.build();
		ReviewerEntity reviewerEntity2 = ReviewerEntity.builder()
			.id(2L)
			.collaborationExperience(true)
			.position("developer")
			.nickName("jun young")
			.createdAt(LocalDateTime.now())
			.updatedAt(LocalDateTime.now())
			.build();
		ReviewerEntity reviewerEntity3 = ReviewerEntity.builder()
			.id(3L)
			.collaborationExperience(true)
			.position("developer")
			.nickName("do jin")
			.createdAt(LocalDateTime.now())
			.updatedAt(LocalDateTime.now())
			.build();
		FeedbackEntity feedbackEntity1 = FeedbackEntity.builder()
			.id(1L)
			.surveyId(surveyId)
			.isRead(true)
			.reviewer(reviewerEntity1)
			.createdAt(LocalDateTime.now())
			.updatedAt(LocalDateTime.now())
			.formFeedbackEntityList(List.of())
			.build();
		FeedbackEntity feedbackEntity2 = FeedbackEntity.builder()
			.id(2L)
			.surveyId(surveyId)
			.isRead(true)
			.reviewer(reviewerEntity2)
			.createdAt(LocalDateTime.now())
			.updatedAt(LocalDateTime.now())
			.formFeedbackEntityList(List.of())
			.build();
		FeedbackEntity feedbackEntity3 = FeedbackEntity.builder()
			.id(3L)
			.surveyId(surveyId)
			.isRead(false)
			.reviewer(reviewerEntity3)
			.createdAt(LocalDateTime.now())
			.updatedAt(LocalDateTime.now())
			.formFeedbackEntityList(List.of())
			.build();

		testReviewerSaveRepository.saveAndFlush(reviewerEntity1);
		testReviewerSaveRepository.saveAndFlush(reviewerEntity2);
		testReviewerSaveRepository.saveAndFlush(reviewerEntity3);
		testFeedbackSaveRepository.saveAndFlush(feedbackEntity1);
		testFeedbackSaveRepository.saveAndFlush(feedbackEntity2);
		testFeedbackSaveRepository.saveAndFlush(feedbackEntity3);

		entityManager.clear();

		int resultCount = updatedFeedbackCountAdaptor.getUpdatedFeedbackCountBySurveyId(surveyId);

		assertEquals(1, resultCount);
	}
}
