package me.nalab.survey.application.port.out.persistence.target.update;

import java.util.Optional;

import me.nalab.survey.domain.target.Target;

public interface TargetFindPort {

	/**
	 * targetId에 해당하는 Target을 조회합니다.
	 *
	 * @param targetId Target을 조회할 Target의 ID
	 * @return Optional 만약, 어떠한 targetId도 없을 경우, Optional.empty() 를 반환해야 합니다.
	 */
	Optional<Target> findTarget(Long targetId);

}
