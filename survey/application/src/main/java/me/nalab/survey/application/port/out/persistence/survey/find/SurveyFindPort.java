package me.nalab.survey.application.port.out.persistence.survey.find;

import me.nalab.survey.application.common.dto.SurveyDto;

public interface SurveyFindPort {

	SurveyDto findSurvey(Long surveyId);

}
