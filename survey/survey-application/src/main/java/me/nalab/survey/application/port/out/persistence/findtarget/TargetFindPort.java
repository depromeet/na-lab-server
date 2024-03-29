package me.nalab.survey.application.port.out.persistence.findtarget;

import java.util.List;
import java.util.Optional;
import java.util.Set;
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
	Optional<Target> findTargetById(Long targetId);

	/**
	 * targetId를 받아 Target을 반환합니다.
	 * targetId에 해당하는 Target이 없다면 예외를 던집니다.
	 */
	Target getTargetById(Long targetId);

	/**
	 * surveyId 들로 Target들을 조회합니다.
	 */
	List<Target> findAllTargetBySurveyIds(Set<Long> surveyIds);
}
