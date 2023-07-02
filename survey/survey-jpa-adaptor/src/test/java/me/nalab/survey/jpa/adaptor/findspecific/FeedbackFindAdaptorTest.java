package me.nalab.survey.jpa.adaptor.findspecific;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import me.nalab.core.data.feedback.FeedbackEntity;
import me.nalab.survey.application.port.out.persistence.findspecific.FeedbackFindPort;
import me.nalab.survey.domain.feedback.Feedback;
import me.nalab.survey.domain.survey.Survey;
import me.nalab.survey.jpa.adaptor.RandomFeedbackFixture;
import me.nalab.survey.jpa.adaptor.RandomSurveyFixture;
import me.nalab.survey.jpa.adaptor.common.mapper.FeedbackEntityMapper;

@DataJpaTest
@EnableJpaRepositories
@EntityScan("me.nalab.core.data")
@ContextConfiguration(classes = FeedbackFindAdaptor.class)
@TestPropertySource("classpath:h2.properties")
class FeedbackFindAdaptorTest {

	@Autowired
	private FeedbackFindPort feedbackFindPort;

	@Autowired
	private TestFeedbackFindJpaRepository testFeedbackFindJpaRepository;

	@Test
	void FIND_FEEDBACK_WITH_SUCCESS() {
		Survey survey = RandomSurveyFixture.createRandomSurvey();
		Feedback feedback = RandomFeedbackFixture.getRandomFeedbackBySurvey(survey);
		FeedbackEntity feedbackEntity = FeedbackEntityMapper.toEntity(feedback);
		testFeedbackFindJpaRepository.save(feedbackEntity);

		Optional<Feedback> resultFeedback = feedbackFindPort.findFeedback(feedback.getId());

		assertTrue(resultFeedback.isPresent());
		assertEquals(feedback, resultFeedback.get());
	}

	@Test
	void FIND_FEEDBACK_WITH_FAIL() {

		Long nonExistentFeedbackId = 999L;

		Optional<Feedback> resultFeedback = feedbackFindPort.findFeedback(nonExistentFeedbackId);

		assertTrue(resultFeedback.isEmpty());
	}

}
