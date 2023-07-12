package me.nalab.survey.jpa.adaptor.findfeedback.formtype;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import me.nalab.core.data.survey.SurveyEntity;
import me.nalab.survey.application.port.out.persistence.findfeedback.formtype.TargetIdFindPort;
import me.nalab.survey.jpa.adaptor.findfeedback.formtype.repository.SurveyFindRepository;

@Repository("findfeedback.formtype.TargetIdFindAdaptor")
public class TargetIdFindAdaptor implements TargetIdFindPort {

	private final SurveyFindRepository surveyFindRepository;

	@Autowired
	TargetIdFindAdaptor(
		@Qualifier("findfeedback.formtype.SurveyFindRepository") SurveyFindRepository surveyFindRepository) {
		this.surveyFindRepository = surveyFindRepository;
	}

	@Override
	public Optional<Long> findTargetIdBySurveyId(Long surveyId) {
		Optional<SurveyEntity> surveyEntity = surveyFindRepository.findById(surveyId);
		if (surveyEntity.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(surveyEntity.get().getTargetId());
	}

}
