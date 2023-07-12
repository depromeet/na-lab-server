package me.nalab.survey.jpa.adaptor.findtarget;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import me.nalab.core.data.survey.SurveyEntity;
import me.nalab.survey.application.port.out.persistence.findtarget.TargetIdFindPort;
import me.nalab.survey.jpa.adaptor.findtarget.repository.TargetIdFindJpaRepository;

@Repository("findtarget.TargetIdFindAdaptor")
public class TargetIdFindAdaptor implements TargetIdFindPort {

	private final TargetIdFindJpaRepository targetIdFindJpaRepository;

	@Autowired
	TargetIdFindAdaptor(
		@Qualifier("findtarget.TargetIdFindJpaRepository") TargetIdFindJpaRepository targetIdFindJpaRepository) {
		this.targetIdFindJpaRepository = targetIdFindJpaRepository;
	}

	@Override
	public Optional<Long> findTargetIdBySurveyId(Long surveyId) {
		Optional<SurveyEntity> surveyEntity = targetIdFindJpaRepository.findById(surveyId);
		if (surveyEntity.isEmpty()) {
			return Optional.empty();
		}

		Long targetId = surveyEntity.get().getTargetId();
		return Optional.of(targetId);
	}

}
