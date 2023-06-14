package me.nalab.survey.jpa.adaptor.find;

import lombok.RequiredArgsConstructor;
import me.nalab.core.data.survey.SurveyEntity;
import me.nalab.core.data.target.TargetEntity;
import me.nalab.survey.application.port.out.persistence.target.find.TargetFindPort;
import me.nalab.survey.domain.target.Target;
import me.nalab.survey.jpa.adaptor.common.mapper.TargetEntityMapper;
import me.nalab.survey.jpa.adaptor.find.repository.SurveyFindRepository;
import me.nalab.survey.jpa.adaptor.find.repository.TargetFindRepository;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

	@Override
	public List<Target> findAllByUsername(String username) {
		var allTargetEntity = targetFindRepository.findAllByNicknameOrderByCreatedAt(username);
		if (allTargetEntity.isEmpty()) {
			return Collections.emptyList();
		}

		return allTargetEntity.stream().map(TargetEntityMapper::toTarget).collect(Collectors.toList());
	}
}
