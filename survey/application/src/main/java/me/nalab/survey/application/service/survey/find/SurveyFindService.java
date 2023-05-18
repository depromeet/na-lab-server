package me.nalab.survey.application.service.survey.find;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import me.nalab.survey.application.common.dto.SurveyDto;
import me.nalab.survey.application.port.in.web.SurveyFindUseCase;
import me.nalab.survey.application.port.out.persistence.survey.find.SurveyFindPort;

@Service
@RequiredArgsConstructor
public class SurveyFindService implements SurveyFindUseCase {

	private final SurveyFindPort surveyFindPort;

	@Override
	public SurveyDto findSurvey(Long surveyId) {
		return surveyFindPort.findSurvey(surveyId);
	}
}
