package me.nalab.survey.jpa.adaptor.bookmark;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import me.nalab.core.data.feedback.FormFeedbackEntity;
import me.nalab.survey.application.port.out.persistence.bookmark.FormQuestionFeedbackUpdatePort;
import me.nalab.survey.domain.feedback.FormQuestionFeedbackable;
import me.nalab.survey.jpa.adaptor.bookmark.repository.FormQuestionFeedbackUpdateRepository;
import me.nalab.survey.jpa.adaptor.common.mapper.FeedbackEntityMapper;

@Repository
@RequiredArgsConstructor
public class FormQuestionFeedbackUpdateAdaptor implements FormQuestionFeedbackUpdatePort {

	private final FormQuestionFeedbackUpdateRepository formQuestionFeedbackUpdateRepository;

	@Override
	public void updateFormQuestionFeedback(FormQuestionFeedbackable formQuestionFeedbackable) {
		FormFeedbackEntity formFeedbackEntity = FeedbackEntityMapper.toFormFeedbackEntity(formQuestionFeedbackable);
		formFeedbackEntity.setBookmarked(formQuestionFeedbackable.getBookmark().isBookmarked());
		formFeedbackEntity.setBookmarkedAt(formQuestionFeedbackable.getBookmark().getBookmarkedAt());
	}

}
