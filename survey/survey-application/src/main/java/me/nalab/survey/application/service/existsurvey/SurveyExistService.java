package me.nalab.survey.application.service.existsurvey;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.nalab.survey.application.port.in.web.existsurvey.SurveyExistUseCase;
import me.nalab.survey.application.port.out.persistence.existsurvey.SurveyExistCheckPort;

@Service
public class SurveyExistService implements SurveyExistUseCase {

	private final SurveyExistCheckPort surveyExistCheckPort;

	public SurveyExistService(
		@Qualifier("existsurvey.SurveyExistCheckPort") SurveyExistCheckPort surveyExistCheckPort) {
		this.surveyExistCheckPort = surveyExistCheckPort;
	}

	@Override
	@Transactional(readOnly = true)
	public boolean isSurveyExistByTargetId(Long targetId) {
		return surveyExistCheckPort.isSurveyExistByTargetId(targetId);
	}
}
