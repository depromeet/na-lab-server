package me.nalab.survey.application.port.out.persistence;

public interface FindSurveyPort {

	Long findLatestSurveyId(Long targetId);

}
