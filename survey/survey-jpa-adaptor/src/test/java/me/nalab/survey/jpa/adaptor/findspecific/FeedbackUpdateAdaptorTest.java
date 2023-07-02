package me.nalab.survey.jpa.adaptor.findspecific;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import me.nalab.core.data.feedback.FeedbackEntity;
import me.nalab.survey.application.port.out.persistence.findspecific.FeedbackUpdatePort;
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
class FeedbackUpdateAdaptorTest {

	@Autowired
	private FeedbackUpdatePort feedbackUpdatePort;

	@Autowired
	private TestFeedbackFindJpaRepository testFeedbackFindJpaRepository;


	@Test
	void UPDATE_FEEDBACK_WITH_SUCCESS() {
		Survey survey = RandomSurveyFixture.createRandomSurvey();
		Feedback feedback = RandomFeedbackFixture.getRandomFeedbackBySurvey(survey);

		feedbackUpdatePort.updateFeedback(feedback);
		FeedbackEntity feedbackEntity = testFeedbackFindJpaRepository.findAll().get(0);

		assertEquals(feedback, FeedbackEntityMapper.toDomain(feedbackEntity));
	}
}
