package me.nalab.survey.application.service.findspecific;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import me.nalab.survey.application.common.feedback.dto.FeedbackDto;
import me.nalab.survey.application.common.feedback.mapper.FeedbackDtoMapper;
import me.nalab.survey.application.common.survey.dto.SurveyDto;
import me.nalab.survey.application.common.survey.mapper.SurveyDtoMapper;
import me.nalab.survey.application.exception.FeedbackDoesNotExistException;
import me.nalab.survey.application.exception.SurveyDoesNotExistException;
import me.nalab.survey.application.exception.SurveyDoesNotHasTargetException;
import me.nalab.survey.application.port.in.web.findspecific.SpecificFindUseCase;
import me.nalab.survey.application.port.out.persistence.findspecific.FeedbackFindPort;
import me.nalab.survey.application.port.out.persistence.findspecific.SurveyFindPort;
import me.nalab.survey.application.port.out.persistence.findspecific.FeedbackUpdatePort;
import me.nalab.survey.domain.feedback.Feedback;
import me.nalab.survey.domain.survey.Survey;

@Service
@RequiredArgsConstructor
public class SpecificFindService implements SpecificFindUseCase {

	private final FeedbackFindPort feedbackFindPort;
	private final SurveyFindPort surveyFindPort;

	private final FeedbackUpdatePort feedbackUpdatePort;

	@Override
	public FeedbackDto findFeedbackByFeedbackId(Long feedbackId) {
		Feedback feedback = feedbackFindPort.findFeedback(feedbackId).orElseThrow(() -> {
			throw new FeedbackDoesNotExistException(feedbackId);
		});
		return FeedbackDtoMapper.toDto(feedback);
	}

	@Override
	public void updateFeedbackIsReadByFeedbackId(Long feedbackId) {
		feedbackUpdatePort.updateFeedbackIsReadByFeedbackId(feedbackId).orElseThrow(() -> {
			throw new FeedbackDoesNotExistException(feedbackId);
		});
	}

	@Override
	public SurveyDto findSurveyBySurveyId(Long surveyId) {
		Survey survey = surveyFindPort.findSurvey(surveyId).orElseThrow(() -> {
			throw new SurveyDoesNotExistException(surveyId);
		});
		Long targetId = surveyFindPort.findTargetIdBySurveyId(surveyId).orElseThrow(() -> {
			throw new SurveyDoesNotHasTargetException(surveyId);
		});
		return SurveyDtoMapper.toSurveyDto(targetId, survey);
	}
}
