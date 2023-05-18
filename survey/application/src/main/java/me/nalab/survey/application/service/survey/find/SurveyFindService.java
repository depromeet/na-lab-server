package me.nalab.survey.application.service.survey.find;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import me.nalab.survey.application.common.dto.SurveyDto;
import me.nalab.survey.application.port.in.web.survey.find.SurveyFindUseCase;
import me.nalab.survey.application.port.out.persistence.survey.find.SurveyFindPort;

@Service
@RequiredArgsConstructor
public class SurveyFindService implements SurveyFindUseCase {

	private final SurveyFindPort surveyFindPort;

	@Override
	@Transactional(readOnly = true)
	public SurveyDto findSurvey(Long surveyId) {
		return surveyFindPort.getSurvey(surveyId);
	}
}
