package me.nalab.survey.application.service.target.find;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import me.nalab.survey.application.common.dto.TargetDto;
import me.nalab.survey.application.port.in.web.target.find.TargetFindUseCase;
import me.nalab.survey.application.port.out.persistence.target.find.TargetFindPort;

@Service
@RequiredArgsConstructor
public class TargetFindService implements TargetFindUseCase {

	private final TargetFindPort targetFindPort;

	@Override
	@Transactional(readOnly = true)
	public TargetDto findTarget(Long targetId) {
		return targetFindPort.getTarget(targetId);
	}
}
