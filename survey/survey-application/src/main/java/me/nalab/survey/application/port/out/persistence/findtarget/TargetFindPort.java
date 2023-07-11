package me.nalab.survey.application.port.out.persistence.findtarget;

import java.util.Optional;

import me.nalab.survey.domain.target.Target;

/**
 * targetId를 받아 Target을 반환하는 인터페이스입니다.
 */
public interface TargetFindPort {

	/**
	 * targetId에 해당하는 Target을 조회합니다
	 *
	 * @param targetId 조회할 Target의 ID
	 * @return Optional 만약, 어떠한 targetId도 없을 경우, Optional.empty() 를 반환해야 합니다
	 */
	Optional<Target> findTarget(Long targetId);

}
