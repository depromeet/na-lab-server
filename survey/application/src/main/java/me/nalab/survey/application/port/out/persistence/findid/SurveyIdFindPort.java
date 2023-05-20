package me.nalab.survey.application.port.out.persistence.findid;

import java.util.Optional;

/**
 * TargetId에 해당하는 Survey의 ID List를 persistence영역에서 조회하는 인터페이스
 */
public interface SurveyIdFindPort {

	/**
	 * targetId를 입력받고, targetId에 해당하는 유저가 생성한 Survey의 Id를 반환합니다.
	 *
	 * @param targetId surveyId를 생성한 유저의 id
	 * @return Optional 만약, 어떤 survey도 찾을 수 없으면 Optional<Long>을 반환해야함
	 */
	Optional<Long> findSurveyIdByTargetId(Long targetId);

}
