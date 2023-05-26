package me.nalab.survey.jpa.adaptor.feedbackreviewers;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import me.nalab.survey.application.common.feedback.dto.FeedbackDto;
import me.nalab.survey.application.common.feedback.mapper.FeedbackDtoMapper;
import me.nalab.survey.domain.feedback.Feedback;
import me.nalab.survey.domain.survey.Survey;
import me.nalab.survey.jpa.adaptor.RandomFeedbackFixture;
import me.nalab.survey.jpa.adaptor.RandomSurveyFixture;
import me.nalab.survey.jpa.adaptor.common.mapper.FeedbackEntityMapper;
import me.nalab.survey.jpa.adaptor.common.mapper.SurveyEntityMapper;

@DataJpaTest
@EnableJpaRepositories
@EntityScan("me.nalab.core.data")
@ContextConfiguration(classes = FeedbacksReviewersAdaptor.class)
@TestPropertySource("classpath:h2.properties")
class FeedbacksReviewersAdaptorTest {

	@Autowired
	private FeedbacksReviewersAdaptor feedbacksReviewersAdaptor;

	@Autowired
	private TestSurveyCreateJpaRepository testSurveyCreateJpaRepository;

	@Autowired
	private TestFeedbackFindJpaRepository testFeedbackFindJpaRepository;

	@Test
	@DisplayName("질문 폼에 피드백이 있는 경우")
	void FIND_FEEDBACKREVIEWERS_WITH_FEEDBACKS() {

		// given
		Long targetId = 10L;
		Survey survey = RandomSurveyFixture.createRandomSurvey();
		Long surveyId = survey.getId();
		testSurveyCreateJpaRepository.saveAndFlush(SurveyEntityMapper.toSurveyEntity(targetId, survey));

		Feedback feedback1 = RandomFeedbackFixture.getRandomFeedbackBySurvey(survey);
		FeedbackEntity feedbackEntity1 = FeedbackEntityMapper.toEntity(feedback1);
		Feedback feedback2 = RandomFeedbackFixture.getRandomFeedbackBySurvey(survey);
		FeedbackEntity feedbackEntity2 = FeedbackEntityMapper.toEntity(feedback2);
		testFeedbackFindJpaRepository.saveAndFlush(feedbackEntity1);
		testFeedbackFindJpaRepository.saveAndFlush(feedbackEntity2);

		List<Feedback> expected = List.of(feedback1, feedback2);

		// when
		List<Feedback> resultFeedbacks = feedbacksReviewersAdaptor.findAllFeedback(surveyId);

		// then
		assertEquals(expected.size(), resultFeedbacks.size());

	}

	@Test
	@DisplayName("질문 폼에 피드백이 없는 경우")
	void FIND_FEEDBACKREVIEWERS_WITH_NO_FEEDBACKS() {

		// given
		Long targetId = 10L;
		int expected = 0;
		Survey survey = RandomSurveyFixture.createRandomSurvey();
		Long surveyId = survey.getId();
		testSurveyCreateJpaRepository.saveAndFlush(SurveyEntityMapper.toSurveyEntity(targetId, survey));

		// when
		List<Feedback> resultFeedbacks = feedbacksReviewersAdaptor.findAllFeedback(surveyId);

		// then
		assertEquals(expected, resultFeedbacks.size());
	}
}
