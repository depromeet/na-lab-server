package me.nalab.survey.application.port.out.persistence.target.find;

import me.nalab.survey.application.common.dto.TargetDto;

public interface TargetFindPort {

	TargetDto findTarget(Long surveyId);
}
