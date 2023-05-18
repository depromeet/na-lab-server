package me.nalab.survey.application.port.out.persistence.survey.find;

import me.nalab.survey.domain.survey.Survey;

public interface SurveyFindPort {

	Survey getSurvey(Long surveyId);

}
