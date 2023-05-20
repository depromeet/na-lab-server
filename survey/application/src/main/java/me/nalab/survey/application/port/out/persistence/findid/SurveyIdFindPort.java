package me.nalab.survey.application.port.out.persistence.findid;

import java.util.List;

/**
 * TargetId에 해당하는 Survey의 ID List를 persistence영역에서 조회하는 인터페이스
 */
public interface SurveyIdFindPort {

	/**
	 * targetId를 입력받고, targetId에 해당하는 유저가 생성한 Survey의 Id를 반환합니다.
	 * 만약, 조회할 수 없다면, 빈 배열을 반환합니다.
	 *
	 * @param targetId surveyId를 생성한 유저의 id
	 * @return List SurveyId의 list
	 */
	List<Long> findAllSurveyIdByTargetId(Long targetId);

}
