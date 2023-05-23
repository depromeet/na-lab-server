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
@ContextConfiguration(classes = TotalFeedbackCountAdaptor.class)
@TestPropertySource("classpath:h2.properties")
class TotalFeedbackCountAdaptorTest {

	@Autowired
	private TotalFeedbackCountAdaptor totalFeedbackCountAdaptor;

	@Autowired
	private TestReviewerSaveRepository testReviewerSaveRepository;

	@Autowired
	private TestTotalFeedbackCountRepository testTotalFeedbackCountRepository;

	@Autowired
	private EntityManager entityManager;

	@Test
	@DisplayName("질문 폼에 해당하는 피드백이 없는 경우")
	void FIND_TOTAL_FEEDBACK_COUNT_TEST_WITH_NO_FEEDBACK() {

		Long surveyId = 1L;
		int resultCount = totalFeedbackCountAdaptor.getTotalFeedbackCountBySurveyId(surveyId);
		assertEquals(resultCount, 0);
	}

	@Test
	@DisplayName("질문 폼에 해당하는 피드백이 있는 경우")
	void FIND_TOTAL_FEEDBACK_COUNT_TEST_WITH_FEEDBACKS() {

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
		testTotalFeedbackCountRepository.saveAndFlush(feedbackEntity1);
		testTotalFeedbackCountRepository.saveAndFlush(feedbackEntity2);

		entityManager.clear();

		int resultCount = totalFeedbackCountAdaptor.getTotalFeedbackCountBySurveyId(surveyId);

		assertEquals(2, resultCount);
	}
}
