package me.nalab.survey.jpa.adaptor.findfeedback;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.Collectors;

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
import me.nalab.survey.application.port.out.persistence.findfeedback.FeedbackUpdatePort;
import me.nalab.survey.domain.feedback.Feedback;
import me.nalab.survey.domain.survey.Survey;
import me.nalab.survey.jpa.adaptor.RandomFeedbackFixture;
import me.nalab.survey.jpa.adaptor.RandomSurveyFixture;
import me.nalab.survey.jpa.adaptor.common.mapper.FeedbackEntityMapper;

@DataJpaTest
@EnableJpaRepositories
@EntityScan("me.nalab.core.data")
@ContextConfiguration(classes = FeedbackUpdateAdaptor.class)
@TestPropertySource("classpath:h2.properties")
public class FeedbackUpdateAdaptorTest {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private FeedbackUpdatePort feedbackUpdatePort;

	@Autowired
	private TestFeedbackUpdateJpaRepository testFeedbackUpdateJpaRepository;

	@Test
	@DisplayName("Feedback 업데이트 테스트")
	void UPDATE_FEEDBACK_SUCCESS_TEST() {
		Survey survey = RandomSurveyFixture.createRandomSurvey();
		Feedback feedback1 = RandomFeedbackFixture.getRandomFeedbackBySurvey(survey);
		Feedback feedback2 = RandomFeedbackFixture.getRandomFeedbackBySurvey(survey);
		testFeedbackUpdateJpaRepository.saveAndFlush(FeedbackEntityMapper.toEntity(feedback1));
		testFeedbackUpdateJpaRepository.saveAndFlush(FeedbackEntityMapper.toEntity(feedback2));
		entityManager.clear();

		List<FeedbackEntity> feedbackEntityList = testFeedbackUpdateJpaRepository.findAll();

		feedbackEntityList.stream()
			.flatMap(feedback -> feedback.getFormFeedbackEntityList().stream())
			.forEach(formQuestionFeedbackable -> formQuestionFeedbackable.setRead(true));

		List<Feedback> feedbackList = feedbackEntityList.stream()
			.map(FeedbackEntityMapper::toDomain)
			.collect(Collectors.toList());

		feedbackUpdatePort.updateFeedback(feedbackList);
		entityManager.flush();
		entityManager.clear();

		List<FeedbackEntity> feedbackEntities = testFeedbackUpdateJpaRepository.findAll();

		feedbackEntities
			.forEach(r -> assertAll(
				r.getFormFeedbackEntityList().stream()
					.map(FormFeedbackEntity -> () -> {
						assertTrue(FormFeedbackEntity.isRead());
					})
			));
	}
}
