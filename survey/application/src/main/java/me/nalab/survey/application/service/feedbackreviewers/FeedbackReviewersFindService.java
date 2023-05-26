package me.nalab.survey.application.service.feedbackreviewers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import me.nalab.survey.application.common.feedback.dto.FeedbackDto;
import me.nalab.survey.application.common.feedback.mapper.FeedbackDtoMapper;
import me.nalab.survey.application.port.in.web.feedbackreviewers.FeedbackReviewersFindUseCase;
import me.nalab.survey.application.port.out.persistence.feedbackreviewers.FeedbacksFindPort;
import me.nalab.survey.domain.feedback.Feedback;

@Service
@RequiredArgsConstructor
public class FeedbackReviewersFindService implements FeedbackReviewersFindUseCase {

	private final FeedbacksFindPort feedbacksFindPort;

	@Override
	public List<FeedbackDto> findAllFeedback(Long surveyId) {
		List<Feedback> feedbacks = feedbacksFindPort.findAllFeedback(surveyId);
		return feedbacks.stream()
			.map(FeedbackDtoMapper::toDto)
			.collect(Collectors.toList());
	}
}
