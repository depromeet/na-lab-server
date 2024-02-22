package me.nalab.survey.application.service.find;

import me.nalab.survey.application.exception.TargetDoesNotHasSurveyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import me.nalab.survey.application.common.survey.dto.SurveyDto;
import me.nalab.survey.application.common.survey.mapper.SurveyDtoMapper;
import me.nalab.survey.application.exception.SurveyDoesNotExistException;
import me.nalab.survey.application.port.in.web.survey.find.SurveyFindUseCase;
import me.nalab.survey.application.port.out.persistence.survey.find.SurveyFindPort;
import me.nalab.survey.application.port.out.persistence.target.find.TargetFindPort;
import me.nalab.survey.domain.survey.Survey;

@Service
@RequiredArgsConstructor
public class SurveyFindService implements SurveyFindUseCase {

	private final TargetFindPort targetFindPort;
	private final SurveyFindPort surveyFindPort;

	@Override
	@Transactional(readOnly = true)
	public SurveyDto findSurvey(Long surveyId) {

		Long targetId = targetFindPort.findTargetIdBySurveyId(surveyId).orElseThrow(() -> {
			throw new SurveyDoesNotExistException(surveyId);
		});

		Survey survey = surveyFindPort.findSurvey(surveyId).orElseThrow(() -> {
			throw new SurveyDoesNotExistException(surveyId);
		});

		return SurveyDtoMapper.toSurveyDto(targetId, survey);
	}

	@Override
	public SurveyDto findSurveyByTargetId(Long targetId) {
		var survey = surveyFindPort.findSurveyByTargetId(targetId)
			.orElseThrow(() -> new TargetDoesNotHasSurveyException(targetId));
		return SurveyDtoMapper.toSurveyDto(targetId, survey);
	}
}
