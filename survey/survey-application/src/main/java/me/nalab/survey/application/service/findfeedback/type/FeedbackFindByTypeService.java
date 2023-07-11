package me.nalab.survey.application.service.findfeedback.type;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import me.nalab.survey.application.common.feedback.dto.FeedbackDto;
import me.nalab.survey.application.common.feedback.mapper.FeedbackDtoMapper;
import me.nalab.survey.application.common.survey.dto.FormQuestionDtoable;
import me.nalab.survey.application.common.survey.mapper.SurveyDtoMapper;
import me.nalab.survey.application.exception.SurveyDoesNotHasFormTypeException;
import me.nalab.survey.application.port.in.web.findfeedback.type.FeedbackFindByTypeUseCase;
import me.nalab.survey.application.port.out.persistence.findfeedback.type.FeedbackFindPort;
import me.nalab.survey.application.port.out.persistence.findfeedback.type.FormQuestionFindPort;
import me.nalab.survey.domain.feedback.Feedback;
import me.nalab.survey.domain.survey.FormQuestionable;

@Service
@RequiredArgsConstructor
public class FeedbackFindByTypeService implements FeedbackFindByTypeUseCase {

	private final FormQuestionFindPort formQuestionFindPort;
	private final FeedbackFindPort feedbackFindPort;

	@Transactional
	@Override
	public List<FormQuestionDtoable> findFormQuestionBySurveyIdAndType(Long surveyId, String formType) {
		List<FormQuestionable> formQuestionableList = formQuestionFindPort.findFormQuestionableBySurveyIdAndType(
			surveyId, formType);
		if (formQuestionableList.isEmpty()) {
			throw new SurveyDoesNotHasFormTypeException(surveyId, formType);
		}
		return formQuestionableList.stream()
			.map(SurveyDtoMapper::getFormQuestionDtoable)
			.collect(Collectors.toList());
	}

	@Transactional
	@Override
	public List<FeedbackDto> findFeedbackBySurveyId(Long surveyId) {
		List<Feedback> feedbackList = feedbackFindPort.findFeedbackBySurveyId(surveyId);
		return feedbackList.stream()
			.map(FeedbackDtoMapper::toDto)
			.collect(Collectors.toList());
	}

}
