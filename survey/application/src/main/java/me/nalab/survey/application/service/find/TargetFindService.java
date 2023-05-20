package me.nalab.survey.application.service.find;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import me.nalab.survey.application.common.dto.TargetDto;
import me.nalab.survey.application.common.mapper.TargetDtoMapper;
import me.nalab.survey.application.exception.TargetDoesNotExistException;
import me.nalab.survey.application.port.in.web.target.find.TargetFindUseCase;
import me.nalab.survey.application.port.out.persistence.target.find.TargetFindPort;
import me.nalab.survey.domain.target.Target;

@Service
@RequiredArgsConstructor
public class TargetFindService implements TargetFindUseCase {

	private final TargetFindPort targetFindPort;

	@Override
	@Transactional(readOnly = true)
	public TargetDto findTarget(Long targetId) {
		Target target = targetFindPort.findTarget(targetId).orElseThrow(() -> {
			throw new TargetDoesNotExistException(targetId);
		});
		return TargetDtoMapper.toTargetDto(target);
	}

}
