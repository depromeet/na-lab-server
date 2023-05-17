package me.nalab.survey.application.port.in.web;

import me.nalab.survey.application.common.dto.SurveyDto;

public interface SurveyFindUseCase {

	SurveyDto findSurvey(Long surveyId);
}
