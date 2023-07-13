package me.nalab.survey.jpa.adaptor.feedbackreviewers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import me.nalab.survey.application.port.out.persistence.feedbackreviewers.SurveyExistCheckPort;
import me.nalab.survey.jpa.adaptor.feedbackreviewers.repository.SurveyExistCheckJpaRepository;

@Repository("feedbackreviewers.SurveyExistCheckAdaptor")
public class SurveyExistCheckAdaptor implements SurveyExistCheckPort {

	private final SurveyExistCheckJpaRepository surveyExistCheckJpaRepository;

	SurveyExistCheckAdaptor(
		@Qualifier("feedbackreviewers.SurveyExistCheckJpaRepository") SurveyExistCheckJpaRepository surveyExistCheckJpaRepository) {
		this.surveyExistCheckJpaRepository = surveyExistCheckJpaRepository;
	}

	@Override
	public boolean isExistSurveyBySurveyId(Long surveyId) {
		return surveyExistCheckJpaRepository.existsById(surveyId);
	}
}
