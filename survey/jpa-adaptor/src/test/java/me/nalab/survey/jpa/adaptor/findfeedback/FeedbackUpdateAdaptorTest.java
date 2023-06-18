package me.nalab.survey.jpa.adaptor.findfeedback;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
	private FeedbackUpdatePort feedbackUpdatePort;

	@Autowired
	private TestFeedbackUpdateJpaRepository testFeedbackUpdateJpaRepository;

	@Test
	@DisplayName("Feedback 저장 테스트")
	void SAVE_FEEDBACK_SUCCESS_TEST() {
		Survey survey = RandomSurveyFixture.createRandomSurvey();
		Feedback feedback = RandomFeedbackFixture.getRandomFeedbackBySurvey(survey);

		feedbackUpdatePort.updateFeedback(feedback);
		FeedbackEntity feedbackEntity = testFeedbackUpdateJpaRepository.findAll().get(0);

		assertEquals(feedback, FeedbackEntityMapper.toDomain(feedbackEntity));

	}
}
