package me.nalab.survey.application.port.out.persistence.target.find;

import me.nalab.survey.domain.target.Target;

import java.util.List;
import java.util.Optional;

public interface TargetFindPort {

	/**
	 * targetId에 해당하는 Target을 조회합니다.
	 *
	 * @param targetId Target을 조회할 Target의 ID
	 * @return Optional 만약, 어떠한 targetId도 없을 경우, Optional.empty() 를 반환해야 합니다.
	 */
	Optional<Target> findTarget(Long targetId);

	/**
	 * surveyId에 해당하는 Target의 id를 조회합니다.
	 *
	 * @param surveyId target이 생성한 survey의 ID
	 * @return Optional 만약, 어떠한 surveyId도 없을 경우, Optional.empty() 를 반환해야 합니다.
	 */
	Optional<Long> findTargetIdBySurveyId(Long surveyId);

	/**
	 * 유저 이름으로 모든 target을 조회합니다
	 * @param username 유저 이름
	 * @return 유저 이름으로 생성된 모든 target
	 */
	List<Target> findAllByUsername(String username);
}
