package me.nalab.survey.application.service.bookmark;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import me.nalab.survey.application.RandomFeedbackDtoFixture;
import me.nalab.survey.application.RandomSurveyDtoFixture;
import me.nalab.survey.application.TestIdGenerator;
import me.nalab.survey.application.common.feedback.dto.FeedbackDto;
import me.nalab.survey.application.common.feedback.mapper.FeedbackDtoMapper;
import me.nalab.survey.application.common.survey.mapper.SurveyDtoMapper;
import me.nalab.survey.application.exception.FormQuestionFeedbackNotExistException;
import me.nalab.survey.application.port.out.persistence.bookmark.FormQuestionFeedbackFindPort;
import me.nalab.survey.application.port.out.persistence.bookmark.FormQuestionFeedbackUpdatePort;
import me.nalab.survey.domain.feedback.Feedback;
import me.nalab.survey.domain.feedback.FormQuestionFeedbackable;
import me.nalab.survey.domain.survey.Survey;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestIdGenerator.class})
class ReplaceBookmarkServiceTest {

	private ReplaceBookmarkService replaceBookmarkService;

	@Autowired
	private TestIdGenerator testIdGenerator;

	@Mock
	private FormQuestionFeedbackFindPort formQuestionFeedbackFindPort;

	@Mock
	private FormQuestionFeedbackUpdatePort formQuestionFeedbackUpdatePort;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		replaceBookmarkService = new ReplaceBookmarkService(formQuestionFeedbackFindPort,
			formQuestionFeedbackUpdatePort);
	}

	@Test
	void UPDATE_BOOKMARK_BY_FORM_QUESTION_FEEDBACK_ID() {

		RandomSurveyDtoFixture.setRandomIdGenerator(() -> testIdGenerator.generate());
		Survey survey = SurveyDtoMapper.toSurvey(RandomSurveyDtoFixture.createRandomSurveyDto());
		FeedbackDto feedbackDto = RandomFeedbackDtoFixture.getRandomFeedbackDtoBySurvey(survey);
		Feedback feedback = FeedbackDtoMapper.toDomain(survey, feedbackDto);
		FormQuestionFeedbackable formQuestionFeedback = feedback.getFormQuestionFeedbackableList().get(0);
		boolean isBookmarked = formQuestionFeedback.getBookmark().isBookmarked();
		Long formQuestionFeedbackId = formQuestionFeedback.getId();

		when(formQuestionFeedbackFindPort.findFormQuestionFeedbackById(formQuestionFeedbackId)).
			thenReturn(Optional.of(formQuestionFeedback));
		replaceBookmarkService.replaceBookmark(formQuestionFeedbackId);

		verify(formQuestionFeedbackUpdatePort).updateFormQuestionFeedback(formQuestionFeedback);
		assertThat(formQuestionFeedback.getBookmark().isBookmarked()).isEqualTo(!isBookmarked);
	}

	@Test
	void UPDATE_BOOKMARK_BY_NON_EXISTING_FORM_QUESTION_FEEDBACK_ID() {

		Long formQuestionFeedbackId = 123L;
		assertThrows(FormQuestionFeedbackNotExistException.class,
			() -> replaceBookmarkService.replaceBookmark(formQuestionFeedbackId));
	}

}