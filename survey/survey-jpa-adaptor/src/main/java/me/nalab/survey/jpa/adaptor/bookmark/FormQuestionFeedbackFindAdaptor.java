package me.nalab.survey.jpa.adaptor.bookmark;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import me.nalab.core.data.feedback.FormFeedbackEntity;
import me.nalab.survey.application.port.out.persistence.bookmark.FormQuestionFeedbackFindPort;
import me.nalab.survey.domain.feedback.FormQuestionFeedbackable;
import me.nalab.survey.jpa.adaptor.bookmark.repository.FormQuestionFeedbackFindRepository;
import me.nalab.survey.jpa.adaptor.common.mapper.FeedbackEntityMapper;

@Repository
@RequiredArgsConstructor
public class FormQuestionFeedbackFindAdaptor implements FormQuestionFeedbackFindPort {

	private final FormQuestionFeedbackFindRepository formQuestionFeedbackFindRepository;

	@Override
	public Optional<FormQuestionFeedbackable> findFormQuestionFeedbackById(Long formQuestionFeedbackId) {
		Optional<FormFeedbackEntity> formFeedbackEntity = formQuestionFeedbackFindRepository.findById(
			formQuestionFeedbackId);
		if (formFeedbackEntity.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(FeedbackEntityMapper.toFormQuestionFeedbackable(formFeedbackEntity.get()));
	}

}
