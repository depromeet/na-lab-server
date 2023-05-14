package me.nalab.survey.application.port.in.create;

public interface CreateSurveyUseCase {

	void createSurvey(SurveyCreateRequest request);

	Long findLatestSurveyId(Long targetId);

}