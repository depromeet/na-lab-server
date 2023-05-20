package me.nalab.survey.jpa.adaptor.find;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import me.nalab.core.data.survey.SurveyEntity;
import me.nalab.core.data.target.TargetEntity;
import me.nalab.survey.application.port.out.persistence.target.find.TargetFindPort;
import me.nalab.survey.domain.target.Target;
import me.nalab.survey.jpa.adaptor.common.mapper.TargetEntityMapper;
import me.nalab.survey.jpa.adaptor.find.repository.SurveyFindRepository;
import me.nalab.survey.jpa.adaptor.find.repository.TargetFindRepository;

@Repository
@RequiredArgsConstructor
public class TargetFindAdaptor implements TargetFindPort {

	private final SurveyFindRepository surveyFindRepository;
	private final TargetFindRepository targetFindRepository;

	@Override
	public Optional<Target> findTarget(Long targetId) {
		Optional<TargetEntity> targetEntity = targetFindRepository.findById(targetId);
		if(targetEntity.isEmpty()) {
			return Optional.empty();
		}
		Target target = TargetEntityMapper.toTarget(targetEntity.get());
		return Optional.of(target);
	}

	@Override
	public Optional<Long> findTargetIdBySurveyId(Long surveyId) {
		Optional<SurveyEntity> surveyEntity = surveyFindRepository.findById(surveyId);
		if(surveyEntity.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(surveyEntity.get().getTargetId());
	}
}
