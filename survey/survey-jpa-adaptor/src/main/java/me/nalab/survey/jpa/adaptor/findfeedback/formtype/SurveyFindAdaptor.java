package me.nalab.survey.jpa.adaptor.findfeedback.formtype;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import me.nalab.core.data.survey.SurveyEntity;
import me.nalab.survey.application.port.out.persistence.findfeedback.formtype.SurveyFindPort;
import me.nalab.survey.domain.survey.Survey;
import me.nalab.survey.jpa.adaptor.common.mapper.SurveyEntityMapper;
import me.nalab.survey.jpa.adaptor.findfeedback.formtype.repository.SurveyFindRepository;

@Repository("findfeedback.formtype.SurveyFindAdaptor")
public class SurveyFindAdaptor implements SurveyFindPort {

	private final SurveyFindRepository surveyFindRepository;

	@Autowired
	SurveyFindAdaptor(
		@Qualifier("findfeedback.formtype.SurveyFindRepository") SurveyFindRepository surveyFindRepository) {
		this.surveyFindRepository = surveyFindRepository;
	}

	@Override
	public Optional<Survey> findSurveyById(Long surveyId) {
		Optional<SurveyEntity> surveyEntity = surveyFindRepository.findById(surveyId);
		if (surveyEntity.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(SurveyEntityMapper.toSurvey(surveyEntity.get()));
	}

}
