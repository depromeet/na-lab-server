package me.nalab.survey.application.service.existsurvey;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import me.nalab.survey.application.port.in.web.existsurvey.SurveyExistUseCase;
import me.nalab.survey.application.port.out.persistence.existsurvey.SurveyExistPort;

@Service
@RequiredArgsConstructor
public class SurveyExistService implements SurveyExistUseCase {

	private final SurveyExistPort surveyExistPort;

	@Override
	@Transactional(readOnly = true)
	public boolean existSurveyByTargetId(Long targetId) {
		return surveyExistPort.isSurveyExistByTargetId(targetId);
	}
}
