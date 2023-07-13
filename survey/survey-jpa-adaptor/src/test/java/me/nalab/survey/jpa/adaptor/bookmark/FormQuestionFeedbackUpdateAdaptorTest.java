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
import me.nalab.survey.application.port.out.persistence.bookmark.FormQuestionFeedbackUpdatePort;
import me.nalab.survey.domain.feedback.Feedback;
import me.nalab.survey.domain.feedback.FormQuestionFeedbackable;
import me.nalab.survey.domain.survey.Survey;
import me.nalab.survey.jpa.adaptor.RandomFeedbackFixture;
import me.nalab.survey.jpa.adaptor.RandomSurveyFixture;
import me.nalab.survey.jpa.adaptor.common.mapper.FeedbackEntityMapper;

@DataJpaTest
@EnableJpaRepositories
@EntityScan("me.nalab.core.data")
@ContextConfiguration(classes = {FormQuestionFeedbackFindAdaptor.class, FormQuestionFeedbackUpdateAdaptor.class})
@TestPropertySource("classpath:h2.properties")
class FormQuestionFeedbackUpdateAdaptorTest {

	@Autowired
	private FormQuestionFeedbackFindPort formQuestionFeedbackFindPort;

	@Autowired
	private FormQuestionFeedbackUpdatePort formQuestionFeedbackUpdatePort;

	@Autowired
	private TestFeedbackSaveJpaRepository testFeedbackSaveJpaRepository;

	@Test
	void UPDATE_FORM_QUESTION_FEEDBACK_WITH_SUCCESS() {
		Survey survey = RandomSurveyFixture.createRandomSurvey();
		Feedback feedback = RandomFeedbackFixture.getRandomFeedbackBySurvey(survey);
		FeedbackEntity feedbackEntity = FeedbackEntityMapper.toEntity(feedback);
		FormFeedbackEntity formFeedbackEntity = feedbackEntity.getFormFeedbackEntityList().get(0);
		Long formFeedbackEntityId = FeedbackEntityMapper.toFormQuestionFeedbackable(formFeedbackEntity)
			.getFormQuestionId();
		testFeedbackSaveJpaRepository.save(feedbackEntity);

		FormQuestionFeedbackable formQuestionFeedbackable = formQuestionFeedbackFindPort.findFormQuestionFeedbackById(
			formFeedbackEntityId).get();
		formQuestionFeedbackable.replaceBookmark();
		formQuestionFeedbackUpdatePort.updateFormQuestionFeedback(formQuestionFeedbackable);

		Optional<FormQuestionFeedbackable> resultFormQuestionFeedbackable = formQuestionFeedbackFindPort.findFormQuestionFeedbackById(
			formFeedbackEntityId);
		assertEquals(formQuestionFeedbackable, resultFormQuestionFeedbackable.get());
	}

}
