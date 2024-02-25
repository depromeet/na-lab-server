package me.nalab.survey.jpa.adaptor.findtarget;

import java.util.Optional;

import me.nalab.survey.application.exception.TargetDoesNotExistException;
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

	@Autowired
	TargetFindAdaptor(
		@Qualifier("findtarget.TargetFindJpaRepository") TargetFindJpaRepository targetFindJpaRepository) {
		this.targetFindJpaRepository = targetFindJpaRepository;
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
}
