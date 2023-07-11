package me.nalab.survey.jpa.adaptor.updatetarget;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import me.nalab.core.data.target.TargetEntity;
import me.nalab.survey.application.port.out.persistence.target.update.TargetFindPort;
import me.nalab.survey.domain.target.Target;
import me.nalab.survey.jpa.adaptor.common.mapper.TargetEntityMapper;
import me.nalab.survey.jpa.adaptor.updatetarget.repository.TargetFindRepository;

@Repository("updatetarget.TargetFindAdaptor")
public class TargetFindAdaptor implements TargetFindPort {

	private final TargetFindRepository targetFindRepository;

	@Autowired
	TargetFindAdaptor(
		@Qualifier("updatetarget.TargetFindRepository") TargetFindRepository targetFindRepository) {
		this.targetFindRepository = targetFindRepository;
	}

	@Override
	public Optional<Target> findTarget(Long targetId) {
		Optional<TargetEntity> targetEntity = targetFindRepository.findById(targetId);
		if (targetEntity.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(TargetEntityMapper.toTarget(targetEntity.get()));
	}

}
