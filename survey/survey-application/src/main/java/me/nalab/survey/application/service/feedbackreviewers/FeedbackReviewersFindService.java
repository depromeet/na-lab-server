package me.nalab.survey.application.service.feedbackreviewers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import me.nalab.survey.application.common.feedback.dto.FeedbackDto;
import me.nalab.survey.application.common.feedback.mapper.FeedbackDtoMapper;
import me.nalab.survey.application.exception.SurveyDoesNotExistException;
import me.nalab.survey.application.port.in.web.feedbackreviewers.FeedbackReviewersFindUseCase;
import me.nalab.survey.application.port.out.persistence.feedbackreviewers.FeedbacksFindPort;
import me.nalab.survey.application.port.out.persistence.feedbackreviewers.SurveyExistCheckPort;
import me.nalab.survey.domain.feedback.Feedback;

@Service
@RequiredArgsConstructor
public class FeedbackReviewersFindService implements FeedbackReviewersFindUseCase {

	private final FeedbacksFindPort feedbacksFindPort;

	private final SurveyExistCheckPort surveyExistCheckPort;

	@Override
	public List<FeedbackDto> findAllFeedback(Long surveyId) {
		throwIfSurveyDoesNotExist(surveyId);
		List<Feedback> feedbacks = feedbacksFindPort.findAllFeedback(surveyId);
		return feedbacks.stream()
			.map(FeedbackDtoMapper::toDto)
			.collect(Collectors.toList());
	}

	private void throwIfSurveyDoesNotExist(Long surveyId) {
		if(!surveyExistCheckPort.isExistSurveyBySurveyId(surveyId)) {
			throw new SurveyDoesNotExistException(surveyId);
		}
	}
}
