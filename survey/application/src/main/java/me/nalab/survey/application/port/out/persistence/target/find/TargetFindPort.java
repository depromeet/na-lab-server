package me.nalab.survey.application.port.out.persistence.target.find;

import me.nalab.survey.domain.target.Target;

public interface TargetFindPort {

	Target getTarget(Long targetId);

	Long getTargetId(Long surveyId);
}
