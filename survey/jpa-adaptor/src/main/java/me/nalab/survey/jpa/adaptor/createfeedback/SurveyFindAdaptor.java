package me.nalab.survey.jpa.adaptor.createfeedback;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import me.nalab.core.data.survey.SurveyEntity;
import me.nalab.survey.application.port.out.persistence.createfeedback.SurveyFindPort;
import me.nalab.survey.domain.survey.Survey;
import me.nalab.survey.jpa.adaptor.common.mapper.SurveyEntityMapper;
import me.nalab.survey.jpa.adaptor.createfeedback.repository.SurveyFindJpaRepository;

@Repository("createfeedback.SurveyFindAdaptor")
public class SurveyFindAdaptor implements SurveyFindPort {

	private final SurveyFindJpaRepository surveyFindJpaRepository;

	SurveyFindAdaptor(@Qualifier("createfeedback.SurveyFindJpaRepository") SurveyFindJpaRepository surveyFindJpaRepository) {
		this.surveyFindJpaRepository = surveyFindJpaRepository;
	}

	@Override
	public Optional<Survey> findSurveyBySurveyId(Long surveyId) {
		SurveyEntity surveyEntity = surveyFindJpaRepository.findById(surveyId)
			.orElse(null);
		if(surveyEntity == null) {
			return Optional.empty();
		}
		return Optional.of(SurveyEntityMapper.toSurvey(surveyEntity));
	}

}
