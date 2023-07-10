package me.nalab.survey.jpa.adaptor.updatetarget;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import me.nalab.core.data.target.TargetEntity;
import me.nalab.survey.application.port.out.persistence.target.update.TargetPositionUpdatePort;
import me.nalab.survey.domain.target.Target;
import me.nalab.survey.jpa.adaptor.updatetarget.repository.TargetPositionUpdateRepository;

@Repository("updatetarget.TargetPositionUpdateAdaptor")
public class TargetPositionUpdateAdaptor implements TargetPositionUpdatePort {

	private final TargetPositionUpdateRepository targetPositionUpdateRepository;

	@Autowired
	TargetPositionUpdateAdaptor(
		@Qualifier("updatetarget.TargetPositionUpdateRepository") TargetPositionUpdateRepository targetPositionUpdateRepository) {
		this.targetPositionUpdateRepository = targetPositionUpdateRepository;
	}

	@Override
	public void updateTargetPosition(Target target) {
		Optional<TargetEntity> targetEntity = targetPositionUpdateRepository.findById(target.getId());
		if (targetEntity.isEmpty())
			return;
		targetEntity.get().setPosition(target.getPosition());
	}

}
