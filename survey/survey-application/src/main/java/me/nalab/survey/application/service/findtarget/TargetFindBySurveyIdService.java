package me.nalab.survey.application.service.findtarget;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import me.nalab.survey.application.common.survey.dto.TargetDto;
import me.nalab.survey.application.common.survey.mapper.TargetDtoMapper;
import me.nalab.survey.application.exception.SurveyDoesNotHasTargetException;
import me.nalab.survey.application.exception.TargetDoesNotExistException;
import me.nalab.survey.application.port.in.web.findtarget.TargetFindBySurveyIdUseCase;
import me.nalab.survey.application.port.out.persistence.findtarget.TargetFindPort;
import me.nalab.survey.application.port.out.persistence.findtarget.TargetIdFindPort;
import me.nalab.survey.domain.target.Target;

@Service
@RequiredArgsConstructor
public class TargetFindBySurveyIdService implements TargetFindBySurveyIdUseCase {

	private final TargetFindPort targetFindPort;
	private final TargetIdFindPort targetIdFindPort;

	@Override
	@Transactional
	public TargetDto findTargetBySurveyId(Long surveyId) {
		Long targetId = targetIdFindPort.findTargetIdBySurveyId(surveyId).orElseThrow(() -> {
			throw new SurveyDoesNotHasTargetException(surveyId);
		});
		Target target = targetFindPort.findTarget(targetId).orElseThrow(() -> {
			throw new TargetDoesNotExistException(targetId);
		});
		return TargetDtoMapper.toTargetDto(target);
	}

}
