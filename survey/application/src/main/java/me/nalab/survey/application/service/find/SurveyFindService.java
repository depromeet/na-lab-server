package me.nalab.survey.application.service.find;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import me.nalab.survey.application.common.dto.SurveyDto;
import me.nalab.survey.application.common.mapper.SurveyDtoMapper;
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
			throw new IllegalStateException(
				"Cannot find any survey. \"This method must be called after survey has been created.\""
			);
		});

		Survey survey = surveyFindPort.findSurvey(surveyId).orElseThrow(() -> {
			throw new IllegalStateException(
				"Cannot find any survey. \"This method must be called after survey has been created.\""
			);
		});
		return SurveyDtoMapper.toSurveyDto(targetId, survey);
	}
}
