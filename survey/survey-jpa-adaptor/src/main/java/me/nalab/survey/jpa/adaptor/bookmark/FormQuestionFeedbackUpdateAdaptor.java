package me.nalab.survey.jpa.adaptor.bookmark;

import lombok.RequiredArgsConstructor;
import me.nalab.core.data.feedback.FormFeedbackEntity;
import me.nalab.survey.application.port.out.persistence.bookmark.FormQuestionFeedbackUpdatePort;
import me.nalab.survey.domain.feedback.FormQuestionFeedbackable;
import me.nalab.survey.jpa.adaptor.bookmark.repository.FormQuestionFeedbackUpdateRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FormQuestionFeedbackUpdateAdaptor implements FormQuestionFeedbackUpdatePort {

	private final FormQuestionFeedbackUpdateRepository formQuestionFeedbackUpdateRepository;

	@Override
	public void updateFormQuestionFeedback(FormQuestionFeedbackable formQuestionFeedbackable) {
		FormFeedbackEntity formFeedbackEntity = formQuestionFeedbackUpdateRepository.findById(formQuestionFeedbackable.getId()).orElseThrow();
		formFeedbackEntity.setBookmarked(formQuestionFeedbackable.getBookmark().isBookmarked());
		formFeedbackEntity.setBookmarkedAt(formQuestionFeedbackable.getBookmark().getBookmarkedAt());
	}

}
