package me.nalab.survey.application.port.out.persistence;

public interface LatestSurveyIdFindPort {

	Long findLatestCreatedSurveyId(Long targetId);

}
