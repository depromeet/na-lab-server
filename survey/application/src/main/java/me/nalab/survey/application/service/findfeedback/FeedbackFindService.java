package me.nalab.survey.application.service.findfeedback;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.nalab.survey.application.common.feedback.dto.FeedbackDto;
import me.nalab.survey.application.common.feedback.mapper.FeedbackDtoMapper;
import me.nalab.survey.application.exception.SurveyDoesNotExistException;
import me.nalab.survey.application.port.in.web.findfeedback.FeedbackFindUseCase;
import me.nalab.survey.application.port.out.persistence.findfeedback.FeedbackFindPort;
import me.nalab.survey.application.port.out.persistence.findfeedback.FeedbackUpdatePort;
import me.nalab.survey.application.port.out.persistence.findfeedback.SurveyExistCheckPort;
import me.nalab.survey.domain.feedback.Feedback;
import me.nalab.survey.domain.feedback.FormQuestionFeedbackable;

@Service
public class FeedbackFindService implements FeedbackFindUseCase {

	private final SurveyExistCheckPort surveyExistCheckPort;
	private final FeedbackFindPort feedbackFindPort;

	private final FeedbackUpdatePort feedbackUpdatePort;

	public FeedbackFindService(
		@Qualifier("findfeedback.SurveyExistCheckAdaptor") SurveyExistCheckPort surveyExistCheckPort,
		FeedbackFindPort feedbackFindPort,
		@Qualifier("findfeedback.FeedbackUpdateAdaptor") FeedbackUpdatePort feedbackUpdatePort) {
		this.surveyExistCheckPort = surveyExistCheckPort;
		this.feedbackFindPort = feedbackFindPort;
		this.feedbackUpdatePort = feedbackUpdatePort;
	}

	@Transactional
	@Override
	public List<FeedbackDto> findAllFeedbackDtoBySurveyId(Long surveyId) {
		throwIfSurveyDoesNotExist(surveyId);
		List<Feedback> feedbackList = feedbackFindPort.findAllFeedbackBySurveyId(surveyId);
		Collections.sort(feedbackList);
		return feedbackList.stream().map(FeedbackDtoMapper::toDto).collect(Collectors.toList());
	}

	@Transactional
	@Override
	public void updateFormFeedbackEntityIsReadBySurveyId(Long surveyId) {
		List<Feedback> feedbacks = feedbackFindPort.findAllFeedbackBySurveyId(surveyId);

		if (feedbacks.isEmpty()) return;

		List<List<FormQuestionFeedbackable>> listList = feedbacks.stream()
			.map(Feedback::getFormQuestionFeedbackableList)
			.collect(Collectors.toList());

		listList.stream()
			.flatMap(List::stream)
			.forEach(formQuestionFeedbackable -> formQuestionFeedbackable.setRead(true));

		for (Feedback feedback : feedbacks) {
			feedbackUpdatePort.updateFeedback(feedback);
		}
	}

	private void throwIfSurveyDoesNotExist(Long surveyId) {
		if (surveyExistCheckPort.isExistSurveyBySurveyId(surveyId)) {
			return;
		}
		throw new SurveyDoesNotExistException(surveyId);
	}

}
