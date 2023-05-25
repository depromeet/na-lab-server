package me.nalab.survey.application.service.findfeedback;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import me.nalab.survey.application.common.feedback.dto.FeedbackDto;
import me.nalab.survey.application.common.feedback.mapper.FeedbackDtoMapper;
import me.nalab.survey.application.exception.SurveyDoesNotExistException;
import me.nalab.survey.application.port.in.web.findfeedback.FeedbackFindUseCase;
import me.nalab.survey.application.port.out.persistence.findfeedback.FeedbackFindPort;
import me.nalab.survey.application.port.out.persistence.findfeedback.SurveyExistCheckPort;
import me.nalab.survey.domain.feedback.Feedback;

@Service
@RequiredArgsConstructor
public class FeedbackFindService implements FeedbackFindUseCase {

	private final SurveyExistCheckPort surveyExistCheckPort;
	private final FeedbackFindPort feedbackFindPort;

	@Override
	public List<FeedbackDto> findAllFeedbackDtoBySurveyId(Long surveyId) {
		throwIfSurveyDoesNotExist(surveyId);
		List<Feedback> feedbackList = feedbackFindPort.findAllFeedbackBySurveyId(surveyId);
		Collections.sort(feedbackList);
		return feedbackList.stream().map(FeedbackDtoMapper::toDto).collect(Collectors.toList());
	}

	private void throwIfSurveyDoesNotExist(Long surveyId) {
		if(surveyExistCheckPort.isExistSurveyBySurveyId(surveyId)) {
			return;
		}
		throw new SurveyDoesNotExistException(surveyId);
	}

}
