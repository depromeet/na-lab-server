package me.nalab.survey.application.service.target.find;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import me.nalab.survey.application.common.dto.TargetDto;
import me.nalab.survey.application.port.in.web.TargetFindUseCase;
import me.nalab.survey.application.port.out.persistence.target.find.TargetFindPort;

@Service
@RequiredArgsConstructor
public class TargetFindService implements TargetFindUseCase {

	private final TargetFindPort targetFindPort;
	@Override
	public TargetDto findTarget(Long targetId) {
		return targetFindPort.findTarget(targetId);
	}
}
