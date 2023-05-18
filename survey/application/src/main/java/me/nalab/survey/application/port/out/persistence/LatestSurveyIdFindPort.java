package me.nalab.survey.application.port.out.persistence;

import java.util.Optional;

public interface LatestSurveyIdFindPort {

	Optional<Long> getLatestSurveyIdByTargetId(Long targetId);

}
