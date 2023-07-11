package me.nalab.survey.application.service.findfeedback.type;

import java.util.ArrayList;
import java.util.List;

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
		List<FormQuestionDtoable> formQuestionDtoableList = new ArrayList<>();
		formQuestionableList
			.forEach(q -> formQuestionDtoableList.add(SurveyDtoMapper.getFormQuestionDtoable(q)));
		return formQuestionDtoableList;
	}

	@Transactional
	@Override
	public List<FeedbackDto> findFeedbackBySurveyId(Long surveyId) {
		List<Feedback> feedbackList = feedbackFindPort.findFeedbackBySurveyId(surveyId);
		List<FeedbackDto> feedbackDtoList = new ArrayList<>();
		feedbackList
			.forEach(q -> feedbackDtoList.add(FeedbackDtoMapper.toDto(q)));
		return feedbackDtoList;
	}

}
