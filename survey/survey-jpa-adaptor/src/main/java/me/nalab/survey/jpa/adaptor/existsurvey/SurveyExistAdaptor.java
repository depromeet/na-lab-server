package me.nalab.survey.jpa.adaptor.existsurvey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import me.nalab.survey.application.port.out.persistence.existsurvey.SurveyExistPort;
import me.nalab.survey.jpa.adaptor.existsurvey.repository.SurveyFindRepository;

@Repository("existsurvey.SurveyExistAdaptor")
public class SurveyExistAdaptor implements SurveyExistPort {

	private final SurveyFindRepository surveyFindRepository;

	@Autowired
	SurveyExistAdaptor(
		@Qualifier("existsurvey.SurveyFindRepository") SurveyFindRepository surveyFindRepository) {
		this.surveyFindRepository = surveyFindRepository;
	}

	@Override
	public boolean isSurveyExistByTargetId(Long targetId) {
		return surveyFindRepository.existsByTargetId(targetId);
	}
	
}
