package me.nalab.survey.jpa.adaptor.create;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import me.nalab.survey.application.port.out.persistence.TargetExistCheckPort;
import me.nalab.survey.jpa.adaptor.create.repository.TargetExistJpaRepository;

@Repository
@RequiredArgsConstructor
public class TargetExistCheckAdaptor implements TargetExistCheckPort {

	private final TargetExistJpaRepository targetExistJpaRepository;

	@Override
	public boolean isExistTargetByTargetId(Long targetId) {
		return targetExistJpaRepository.existsById(targetId);
	}

}
