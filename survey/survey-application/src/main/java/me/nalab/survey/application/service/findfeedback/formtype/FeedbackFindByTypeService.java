package me.nalab.survey.application.service.findfeedback.formtype;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import me.nalab.survey.application.common.feedback.dto.FeedbackDto;
import me.nalab.survey.application.common.feedback.mapper.FeedbackDtoMapper;
import me.nalab.survey.application.common.survey.dto.ChoiceFormQuestionDto;
import me.nalab.survey.application.common.survey.dto.FormQuestionDtoable;
import me.nalab.survey.application.common.survey.dto.ShortFormQuestionDto;
import me.nalab.survey.application.common.survey.dto.SurveyDto;
import me.nalab.survey.application.common.survey.mapper.SurveyDtoMapper;
import me.nalab.survey.application.exception.SurveyDoesNotExistException;
import me.nalab.survey.application.exception.SurveyDoesNotHasTargetException;
import me.nalab.survey.application.port.in.web.findfeedback.formtype.FeedbackFindByTypeUseCase;
import me.nalab.survey.application.port.out.persistence.findfeedback.formtype.FeedbackFindPort;
import me.nalab.survey.application.port.out.persistence.findfeedback.formtype.SurveyFindPort;
import me.nalab.survey.application.port.out.persistence.findfeedback.formtype.TargetIdFindPort;
import me.nalab.survey.domain.feedback.Feedback;
import me.nalab.survey.domain.survey.Survey;

@Service
@RequiredArgsConstructor
public class FeedbackFindByTypeService implements FeedbackFindByTypeUseCase {

	private final SurveyFindPort surveyFindPort;
	private final FeedbackFindPort feedbackFindPort;
	private final TargetIdFindPort targetIdFindPort;

	@Transactional(readOnly = true)
	@Override
	public List<FeedbackDto> findFeedbackBySurveyId(Long surveyId) {
		List<Feedback> feedbackList = feedbackFindPort.findFeedbackBySurveyId(surveyId);
		return feedbackList.stream()
			.map(FeedbackDtoMapper::toDto)
			.collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	@Override
	public List<FormQuestionDtoable> formQuestionMatchingWithType(Long surveyId, String formType) {
		SurveyDto surveyDto = findSurvey(surveyId);
		List<FormQuestionDtoable> formQuestionDtoableList = new ArrayList<>();
		surveyDto.getFormQuestionDtoableList()
			.forEach(q ->
				validateFormType(formType, formQuestionDtoableList, q)
			);
		return formQuestionDtoableList;
	}

	private SurveyDto findSurvey(Long surveyId) {
		Survey survey = surveyFindPort.findSurveyById(surveyId).orElseThrow(
			() -> new SurveyDoesNotExistException(surveyId)
		);
		Long targetId = targetIdFindPort.findTargetIdBySurveyId(surveyId).orElseThrow(
			() -> new SurveyDoesNotHasTargetException(surveyId)
		);
		return SurveyDtoMapper.toSurveyDto(targetId, survey);
	}

	private static void validateFormType(String formType, List<FormQuestionDtoable> formQuestionDtoableList,
		FormQuestionDtoable q) {
		if (q instanceof ChoiceFormQuestionDto && ((ChoiceFormQuestionDto)q).getChoiceFormQuestionDtoType()
			.toString()
			.toLowerCase()
			.equals(formType)
			|| q instanceof ShortFormQuestionDto && ((ShortFormQuestionDto)q).getShortFormQuestionDtoType()
			.toString()
			.toLowerCase()
			.equals(formType)) {
			formQuestionDtoableList.add(q);
		}
	}

}
