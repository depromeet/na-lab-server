package me.nalab.survey.jpa.adaptor.findtarget;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import java.util.Set;
import me.nalab.core.data.survey.SurveyEntity;
import me.nalab.survey.application.exception.TargetDoesNotExistException;
import me.nalab.survey.jpa.adaptor.findtarget.repository.TargetIdFindJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import me.nalab.core.data.target.TargetEntity;
import me.nalab.survey.application.port.out.persistence.findtarget.TargetFindPort;
import me.nalab.survey.domain.target.Target;
import me.nalab.survey.jpa.adaptor.common.mapper.TargetEntityMapper;
import me.nalab.survey.jpa.adaptor.findtarget.repository.TargetFindJpaRepository;

@Repository("findtarget.TargetFindAdaptor")
public class TargetFindAdaptor implements TargetFindPort {

	private final TargetFindJpaRepository targetFindJpaRepository;
	private final TargetIdFindJpaRepository targetIdFindJpaRepository;

	@Autowired
	TargetFindAdaptor(
		@Qualifier("findtarget.TargetFindJpaRepository") TargetFindJpaRepository targetFindJpaRepository,
		@Qualifier("findtarget.TargetIdFindJpaRepository") TargetIdFindJpaRepository targetIdFindJpaRepository) {
		this.targetFindJpaRepository = targetFindJpaRepository;
		this.targetIdFindJpaRepository = targetIdFindJpaRepository;
	}

	@Override
	public Optional<Target> findTargetById(Long targetId) {
		Optional<TargetEntity> targetEntity = targetFindJpaRepository.findById(targetId);
		return targetEntity.map(TargetEntityMapper::toTarget);
	}

	@Override
	public Target getTargetById(Long targetId) {
		var targetEntity = targetFindJpaRepository.findById(targetId)
			.orElseThrow(() -> new TargetDoesNotExistException(targetId));

		return TargetEntityMapper.toTarget(targetEntity);
	}

    @Override
    public List<Target> findAllTargetBySurveyIds(Set<Long> surveyIds) {
        var surveys = targetIdFindJpaRepository.findAllById(surveyIds);
        var targetIds = surveys
            .stream()
            .map(SurveyEntity::getTargetId)
            .toList();

        return targetFindJpaRepository.findAllById(targetIds)
            .stream()
            .map(targetEntity -> {
                var surveyEntity = getSurveyEntityByTargetId(targetEntity.getId(), surveys);
                return TargetEntityMapper.toTargetWithSurvey(targetEntity, surveyEntity);
            })
            .toList();
    }

    private SurveyEntity getSurveyEntityByTargetId(Long targetId, List<SurveyEntity> surveyEntities) {
        return surveyEntities.stream()
            .filter(entity -> Objects.equals(entity.getTargetId(), targetId))
            .findFirst().orElseThrow(
                () -> new IllegalStateException("Cannot find exist survey")
            );
    }
}
