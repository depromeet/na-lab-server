package me.nalab.survey.jpa.adaptor.findfeedbacksummary;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import me.nalab.survey.application.port.out.persistence.feedbacksummary.SurveyExistCheckPort;
import me.nalab.survey.jpa.adaptor.findfeedbacksummary.repository.SurveyExistCheckJpaRepository;

@Repository("findfeedbacksummary.SurveyExistCheckAdaptor")
public class SurveyExistCheckAdaptor implements SurveyExistCheckPort {

	private final SurveyExistCheckJpaRepository surveyExistCheckJpaRepository;

	SurveyExistCheckAdaptor(
		@Qualifier("findfeedbacksummary.SurveyExistCheckJpaRepository") SurveyExistCheckJpaRepository surveyExistCheckJpaRepository) {
		this.surveyExistCheckJpaRepository = surveyExistCheckJpaRepository;
	}

	@Override
	public boolean isExistSurveyBySurveyId(Long surveyId) {
		return surveyExistCheckJpaRepository.existsById(surveyId);
	}
}
