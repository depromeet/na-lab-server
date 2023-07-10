package me.nalab.survey.application.service.updatetarget;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import me.nalab.survey.application.exception.TargetDoesNotExistException;
import me.nalab.survey.application.port.in.web.target.update.TargetPositionUpdateUseCase;
import me.nalab.survey.application.port.out.persistence.target.update.TargetFindPort;
import me.nalab.survey.application.port.out.persistence.target.update.TargetPositionUpdatePort;
import me.nalab.survey.domain.target.Target;

@Service
@RequiredArgsConstructor
public class TargetPositionUpdateService implements TargetPositionUpdateUseCase {

	private final TargetFindPort targetFindPort;
	private final TargetPositionUpdatePort targetPositionUpdatePort;

	@Override
	@Transactional
	public void updateTargetPosition(Long targetId, String position) {
		Target target = targetFindPort.findTarget(targetId).orElseThrow(
			() -> new TargetDoesNotExistException(targetId)
		);
		target.setPosition(position);

		boolean updatedResult = targetPositionUpdatePort.updateTargetPosition(target);
		if (!updatedResult) {
			throw new TargetDoesNotExistException(target.getId());
		}
	}

}
