package me.nalab.survey.jpa.adaptor.bookmark;

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
import me.nalab.core.data.feedback.FormFeedbackEntity;
import me.nalab.survey.application.port.out.persistence.bookmark.FormQuestionFeedbackFindPort;
import me.nalab.survey.domain.feedback.Feedback;
import me.nalab.survey.domain.feedback.FormQuestionFeedbackable;
import me.nalab.survey.domain.survey.Survey;
import me.nalab.survey.jpa.adaptor.RandomFeedbackFixture;
import me.nalab.survey.jpa.adaptor.RandomSurveyFixture;
import me.nalab.survey.jpa.adaptor.common.mapper.FeedbackEntityMapper;

@DataJpaTest
@EnableJpaRepositories
@EntityScan("me.nalab.core.data")
@ContextConfiguration(classes = FormQuestionFeedbackFindAdaptor.class)
@TestPropertySource("classpath:h2.properties")
class FormQuestionFeedbackFindAdaptorTest {

	@Autowired
	private FormQuestionFeedbackFindPort formQuestionFeedbackFindPort;

	@Autowired
	private TestFeedbackSaveJpaRepository testFeedbackSaveJpaRepository;

	@Test
	void FIND_FORM_QUESTION_FEEDBACK_WITH_SUCCESS() {
		Survey survey = RandomSurveyFixture.createRandomSurvey();
		Feedback feedback = RandomFeedbackFixture.getRandomFeedbackBySurvey(survey);
		FeedbackEntity feedbackEntity = FeedbackEntityMapper.toEntity(feedback);
		FormFeedbackEntity formFeedbackEntity = feedbackEntity.getFormFeedbackEntityList().get(0);
		FormQuestionFeedbackable formQuestionFeedbackable = FeedbackEntityMapper.toFormQuestionFeedbackable(
			formFeedbackEntity);
		Long formFeedbackEntityId = formFeedbackEntity.getId();
		testFeedbackSaveJpaRepository.save(feedbackEntity);

		Optional<FormQuestionFeedbackable> resultFormQuestionFeedbackable = formQuestionFeedbackFindPort.findFormQuestionFeedbackById(
			formFeedbackEntityId);

		assertTrue(resultFormQuestionFeedbackable.isPresent());
		assertEquals(formQuestionFeedbackable, resultFormQuestionFeedbackable.get());
	}

	@Test
	void FIND_FORM_QUESTION_FEEDBACK_WITH_FAIL() {

		Long nonExistentFormFeedbackEntityId = 999L;

		Optional<FormQuestionFeedbackable> formQuestionFeedbackable = formQuestionFeedbackFindPort.findFormQuestionFeedbackById(
			nonExistentFormFeedbackEntityId);

		assertTrue(formQuestionFeedbackable.isEmpty());
	}

}
