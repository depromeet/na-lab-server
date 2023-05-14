package me.nalab.survey.application.port.out.persistence;

import me.nalab.survey.domain.survey.Survey;

public interface CreateSurveyPort {

	void createSurvey(Survey survey);

}
