package me.nalab.survey.application.port.out.persistence;

import java.util.Optional;

/**
 * 가장 최근에 생성된 SurveyId를 Persistence로 부터 조회하는 역할을 하는 인터페이스 입니다.
 */
public interface LatestSurveyIdFindPort {

	/**
	 * targetId에 해당하는 타겟이 최근에 생성한 Survey의 id를 조회합니다.
	 *
	 * @param targetId surveyId를 조회할 타겟의 ID
	 * @return Optional 만약, 어떠한 SurveyId도 조회할 수 없을경우, Optional.empty() 를 반환해야 합니다.
	 */
	Optional<Long> findLatestSurveyIdByTargetId(Long targetId);

}
