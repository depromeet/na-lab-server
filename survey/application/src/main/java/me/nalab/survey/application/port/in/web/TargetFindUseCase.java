package me.nalab.survey.application.port.in.web;

import me.nalab.survey.application.common.dto.TargetDto;

public interface TargetFindUseCase {
	TargetDto findTarget(Long survey_id);
}
