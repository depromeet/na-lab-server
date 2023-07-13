package me.nalab.survey.jpa.adaptor.findfeedback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import me.nalab.survey.application.port.out.persistence.findfeedback.SurveyExistCheckPort;
import me.nalab.survey.jpa.adaptor.findfeedback.repository.SurveyExistCheckJpaRepository;

@Repository("findfeedback.SurveyExistCheckAdaptor")
public class SurveyExistCheckAdaptor implements SurveyExistCheckPort {

	private final SurveyExistCheckJpaRepository surveyExistCheckJpaRepository;

	@Autowired
	SurveyExistCheckAdaptor(
		@Qualifier("findfeedback.SurveyExistCheckJpaRepository") SurveyExistCheckJpaRepository surveyExistCheckJpaRepository) {
		this.surveyExistCheckJpaRepository = surveyExistCheckJpaRepository;
	}

	@Override
	public boolean isExistSurveyBySurveyId(Long surveyId) {
		return surveyExistCheckJpaRepository.existsById(surveyId);
	}

}
