package me.nalab.survey.application.service.bookmark;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import me.nalab.survey.application.exception.FormQuestionFeedbackNotExistException;
import me.nalab.survey.application.port.in.web.bookmark.BookmarkReplaceUseCase;
import me.nalab.survey.application.port.out.persistence.bookmark.FormQuestionFeedbackFindPort;
import me.nalab.survey.application.port.out.persistence.bookmark.FormQuestionFeedbackUpdatePort;
import me.nalab.survey.domain.feedback.Bookmark;
import me.nalab.survey.domain.feedback.FormQuestionFeedbackable;

@Service
@RequiredArgsConstructor
public class ReplaceBookmarkService implements BookmarkReplaceUseCase {

	private final FormQuestionFeedbackFindPort formQuestionFeedbackFindPort;
	private final FormQuestionFeedbackUpdatePort formQuestionFeedbackUpdatePort;

	@Transactional
	@Override
	public void replaceBookmark(Long formQuestionFeedbackId) {
		FormQuestionFeedbackable formQuestionFeedback = formQuestionFeedbackFindPort.findFormQuestionFeedbackById(
				formQuestionFeedbackId)
			.orElseThrow(() -> new FormQuestionFeedbackNotExistException(formQuestionFeedbackId));
		Bookmark bookmark = formQuestionFeedback.getBookmark();
		bookmark.replaceIsBookmarked();
		formQuestionFeedbackUpdatePort.updateFormQuestionFeedback(formQuestionFeedback);
	}
}
