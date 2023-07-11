package me.nalab.survey.application.port.out.persistence.findtarget;

import java.util.Optional;

/**
 * surveyId를 받아 survey에 해당하는 target의 id를 반환하는 인터페이스입니다.
 */
public interface TargetIdFindPort {

	/**
	 *
	 * @param surveyId survey를 조회할 survey의 ID
	 * @return Optional 만약, 어떠한 targetId도 없을 경우, Optional.empty()
	 */
	Optional<Long> findTargetIdBySurveyId(Long surveyId);

}
